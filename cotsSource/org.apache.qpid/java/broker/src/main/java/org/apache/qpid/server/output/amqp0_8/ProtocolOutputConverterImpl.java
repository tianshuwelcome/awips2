/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP versions:
 *   8-0
 */
package org.apache.qpid.server.output.amqp0_8;

import org.apache.qpid.server.protocol.AMQProtocolSession;
import org.apache.qpid.server.message.AMQMessage;
import org.apache.qpid.server.queue.QueueEntry;
import org.apache.qpid.server.output.ProtocolOutputConverter;
import org.apache.qpid.server.output.HeaderPropertiesConverter;
import org.apache.qpid.server.message.MessageContentSource;
import org.apache.qpid.server.message.MessageTransferMessage;
import org.apache.qpid.framing.*;
import org.apache.qpid.framing.amqp_8_0.BasicGetBodyImpl;
import org.apache.qpid.framing.abstraction.MessagePublishInfo;
import org.apache.qpid.framing.abstraction.ProtocolVersionMethodConverter;
import org.apache.qpid.AMQException;
import org.apache.qpid.transport.DeliveryProperties;

import java.nio.ByteBuffer;

public class ProtocolOutputConverterImpl implements ProtocolOutputConverter
{

    private static final MethodRegistry METHOD_REGISTRY = MethodRegistry.getMethodRegistry(ProtocolVersion.v8_0);

    private static final ProtocolVersionMethodConverter PROTOCOL_CONVERTER =
            METHOD_REGISTRY.getProtocolVersionMethodConverter();

    public static Factory getInstanceFactory()
    {
        return new Factory()
        {

            public ProtocolOutputConverter newInstance(AMQProtocolSession session)
            {
                return new ProtocolOutputConverterImpl(session);
            }
        };
    }

    private final AMQProtocolSession _protocolSession;

    private ProtocolOutputConverterImpl(AMQProtocolSession session)
    {
        _protocolSession = session;
    }


    public AMQProtocolSession getProtocolSession()
    {
        return _protocolSession;
    }

    public void writeDeliver(QueueEntry entry, int channelId, long deliveryTag, AMQShortString consumerTag)
            throws AMQException
    {
        AMQDataBlock deliver = createEncodedDeliverFrame(entry, channelId, deliveryTag, consumerTag);
        writeMessageDelivery(entry.getMessage(), getContentHeaderBody(entry), channelId, deliver);
    }

    private ContentHeaderBody getContentHeaderBody(QueueEntry entry)
            throws AMQException
    {
        if(entry.getMessage() instanceof AMQMessage)
        {
            return ((AMQMessage)entry.getMessage()).getContentHeaderBody();
        }
        else
        {
            final MessageTransferMessage message = (MessageTransferMessage) entry.getMessage();
            BasicContentHeaderProperties props = HeaderPropertiesConverter.convert(message);
            ContentHeaderBody chb = new ContentHeaderBody(props, BasicGetBodyImpl.CLASS_ID);
            chb.bodySize = message.getSize(); 
            return chb;
        }
    }


    public void writeGetOk(QueueEntry entry, int channelId, long deliveryTag, int queueSize) throws AMQException
    {
        AMQDataBlock deliver = createEncodedGetOkFrame(entry, channelId, deliveryTag, queueSize);
        writeMessageDelivery(entry.getMessage(), getContentHeaderBody(entry), channelId, deliver);
    }

    private void writeMessageDelivery(MessageContentSource message, ContentHeaderBody chb, int channelId, AMQDataBlock deliver)
            throws AMQException
    {


        AMQDataBlock contentHeader = ContentHeaderBody.createAMQFrame(channelId, chb);


        final int bodySize = (int) message.getSize();
        if(bodySize == 0)
        {
            SmallCompositeAMQDataBlock compositeBlock = new SmallCompositeAMQDataBlock(deliver,
                                                                             contentHeader);
            writeFrame(compositeBlock);
        }
        else
        {
            int maxBodySize = (int) getProtocolSession().getMaxFrameSize() - AMQFrame.getFrameOverhead();

            final int capacity = bodySize > maxBodySize ? maxBodySize : bodySize;
            ByteBuffer buf = ByteBuffer.allocate(capacity);

            int writtenSize = 0;

            writtenSize += message.getContent(buf, writtenSize);
            buf.flip();
            AMQDataBlock firstContentBody = new AMQFrame(channelId, PROTOCOL_CONVERTER.convertToBody(buf));
            AMQDataBlock[] blocks = new AMQDataBlock[]{deliver, contentHeader, firstContentBody};
            CompositeAMQDataBlock compositeBlock = new CompositeAMQDataBlock(blocks);
            writeFrame(compositeBlock);

            while(writtenSize < bodySize)
            {
                buf = java.nio.ByteBuffer.allocate(capacity);
                writtenSize += message.getContent(buf, writtenSize);
                buf.flip();
                writeFrame(new AMQFrame(channelId, PROTOCOL_CONVERTER.convertToBody(buf)));
            }

        }
    }


    private AMQDataBlock createEncodedDeliverFrame(QueueEntry entry, int channelId, long deliveryTag, AMQShortString consumerTag)
            throws AMQException
    {
        final AMQShortString exchangeName;
        final AMQShortString routingKey;

        if(entry.getMessage() instanceof AMQMessage)
        {
            final AMQMessage message = (AMQMessage) entry.getMessage();
            final MessagePublishInfo pb = message.getMessagePublishInfo();
            exchangeName = pb.getExchange();
            routingKey = pb.getRoutingKey();
        }
        else
        {
            MessageTransferMessage message = (MessageTransferMessage) entry.getMessage();
            DeliveryProperties delvProps = message.getHeader().get(DeliveryProperties.class);
            exchangeName = (delvProps == null || delvProps.getExchange() == null) ? null : new AMQShortString(delvProps.getExchange());
            routingKey = (delvProps == null || delvProps.getRoutingKey() == null) ? null : new AMQShortString(delvProps.getRoutingKey());
        }

        final boolean isRedelivered = entry.isRedelivered();


        BasicDeliverBody deliverBody =
                METHOD_REGISTRY.createBasicDeliverBody(consumerTag,
                                                      deliveryTag,
                                                      isRedelivered,
                                                      exchangeName,
                                                      routingKey);

        AMQFrame deliverFrame = deliverBody.generateFrame(channelId);


        return deliverFrame;
    }

    private AMQDataBlock createEncodedGetOkFrame(QueueEntry entry, int channelId, long deliveryTag, int queueSize)
            throws AMQException
    {
        final AMQShortString exchangeName;
        final AMQShortString routingKey;

        if(entry.getMessage() instanceof AMQMessage)
        {
            final AMQMessage message = (AMQMessage) entry.getMessage();
            final MessagePublishInfo pb = message.getMessagePublishInfo();
            exchangeName = pb.getExchange();
            routingKey = pb.getRoutingKey();
        }
        else
        {
            MessageTransferMessage message = (MessageTransferMessage) entry.getMessage();
            DeliveryProperties delvProps = message.getHeader().get(DeliveryProperties.class);
            exchangeName = (delvProps == null || delvProps.getExchange() == null) ? null : new AMQShortString(delvProps.getExchange());
            routingKey = (delvProps == null || delvProps.getRoutingKey() == null) ? null : new AMQShortString(delvProps.getRoutingKey());
        }

        final boolean isRedelivered = entry.isRedelivered();

        BasicGetOkBody getOkBody =
                METHOD_REGISTRY.createBasicGetOkBody(deliveryTag,
                                                    isRedelivered,
                                                    exchangeName,
                                                    routingKey,
                                                    queueSize);
        AMQFrame getOkFrame = getOkBody.generateFrame(channelId);

        return getOkFrame;
    }

    public byte getProtocolMinorVersion()
    {
        return getProtocolSession().getProtocolMinorVersion();
    }

    public byte getProtocolMajorVersion()
    {
        return getProtocolSession().getProtocolMajorVersion();
    }

    private AMQDataBlock createEncodedReturnFrame(MessagePublishInfo messagePublishInfo, int channelId, int replyCode, AMQShortString replyText) throws AMQException
    {
        BasicReturnBody basicReturnBody =
                METHOD_REGISTRY.createBasicReturnBody(replyCode,
                                                     replyText,
                                                     messagePublishInfo.getExchange(),
                                                     messagePublishInfo.getRoutingKey());
        AMQFrame returnFrame = basicReturnBody.generateFrame(channelId);

        return returnFrame;
    }

    public void writeReturn(MessagePublishInfo messagePublishInfo,
                            ContentHeaderBody header,
                            MessageContentSource content,
                            int channelId,
                            int replyCode,
                            AMQShortString replyText)
            throws AMQException
    {

        AMQDataBlock returnFrame = createEncodedReturnFrame(messagePublishInfo, channelId, replyCode, replyText);

        writeMessageDelivery(content, header, channelId, returnFrame);

    }


    public void writeFrame(AMQDataBlock block)
    {
        getProtocolSession().writeFrame(block);
    }


    public void confirmConsumerAutoClose(int channelId, AMQShortString consumerTag)
    {
        BasicCancelOkBody basicCancelOkBody = METHOD_REGISTRY.createBasicCancelOkBody(consumerTag);
        writeFrame(basicCancelOkBody.generateFrame(channelId));

    }
}
