/**
 * This software was developed and / or modified by Raytheon Company,
 * pursuant to Contract DG133W-05-CQ-1067 with the US Government.
 * 
 * U.S. EXPORT CONTROLLED TECHNICAL DATA
 * This software product contains export-restricted data whose
 * export/transfer/disclosure is restricted by U.S. law. Dissemination
 * to non-U.S. persons whether in the United States or abroad requires
 * an export license or other authorization.
 * 
 * Contractor Name:        Raytheon Company
 * Contractor Address:     6825 Pine Street, Suite 340
 *                         Mail Stop B8
 *                         Omaha, NE 68106
 *                         402.291.0100
 * 
 * See the AWIPS II Master Rights File ("Master Rights File.pdf") for
 * further licensing information.
 **/
package com.raytheon.uf.viz.collaboration.ui.editor;

import org.eclipse.swt.widgets.Event;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;
import com.raytheon.uf.viz.collaboration.comm.identity.CollaborationException;
import com.raytheon.uf.viz.collaboration.comm.identity.ISharedDisplaySession;
import com.raytheon.uf.viz.collaboration.comm.identity.event.IDisplayEvent;
import com.raytheon.uf.viz.collaboration.ui.editor.event.InputEvent;
import com.raytheon.uf.viz.collaboration.ui.editor.event.InputEvent.EventType;
import com.raytheon.uf.viz.core.IDisplayPane;
import com.raytheon.uf.viz.core.rsc.IInputHandler;

/**
 * An input handler that disables mouse and key input if it's not the Session
 * Leader. If it is the Session Leader, it sends out events related to the
 * input.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 20, 2012            njensen     Initial creation
 * 
 * </pre>
 * 
 * @author njensen
 * @version 1.0
 */

public class CollaborationEditorInputHandler implements IInputHandler {

    private static final transient IUFStatusHandler statusHandler = UFStatus
            .getHandler(CollaborationEditorInputHandler.class);

    private ISharedDisplaySession session;

    private IDisplayPane displayPane;

    public CollaborationEditorInputHandler(ISharedDisplaySession session,
            IDisplayPane displayPane) {
        this.session = session;
        this.displayPane = displayPane;
    }

    private void sendEvent(IDisplayEvent event) {
        try {
            session.sendEvent(session.getCurrentDataProvider(), event);
        } catch (CollaborationException e) {
            statusHandler.handle(Priority.PROBLEM, "Error sending input event",
                    e);
        }
    }

    protected boolean isSessionLeader() {
        // TODO does this work?
        // return session.getUserID().equals(session.getCurrentSessionLeader());
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseDown(int, int,
     * int)
     */
    @Override
    public boolean handleMouseDown(int x, int y, int mouseButton) {
        boolean leader = isSessionLeader();
        if (leader) {
            double[] coords = displayPane.screenToGrid(x, y, 0);
            InputEvent event = new InputEvent(EventType.MOUSE_DOWN, coords[0],
                    coords[1], mouseButton);
            sendEvent(event);
        }
        return !leader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseDownMove(int,
     * int, int)
     */
    @Override
    public boolean handleMouseDownMove(int x, int y, int mouseButton) {
        boolean leader = isSessionLeader();
        if (leader) {
            double[] coords = displayPane.screenToGrid(x, y, 0);
            InputEvent event = new InputEvent(EventType.MOUSE_DOWN_MOVE,
                    coords[0], coords[1], mouseButton);
            sendEvent(event);
        }
        return !leader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseUp(int, int,
     * int)
     */
    @Override
    public boolean handleMouseUp(int x, int y, int mouseButton) {
        boolean leader = isSessionLeader();
        if (leader) {
            double[] coords = displayPane.screenToGrid(x, y, 0);
            InputEvent event = new InputEvent(EventType.MOUSE_UP, coords[0],
                    coords[1], mouseButton);
            sendEvent(event);
        }
        return !leader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseHover(int,
     * int)
     */
    @Override
    public boolean handleMouseHover(int x, int y) {
        // TODO doesn't do anything right now to reduce bandwidth
        return !isSessionLeader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseMove(int, int)
     */
    @Override
    public boolean handleMouseMove(int x, int y) {
        // TODO doesn't do anything right now to reduce bandwidth
        return !isSessionLeader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleDoubleClick(int,
     * int, int)
     */
    @Override
    public boolean handleDoubleClick(int x, int y, int button) {
        // TODO doesn't do anything right now to reduce bandwidth
        return !isSessionLeader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseWheel(org.eclipse
     * .swt.widgets.Event, int, int)
     */
    @Override
    public boolean handleMouseWheel(Event event, int x, int y) {
        boolean leader = isSessionLeader();
        if (leader) {
            double[] coords = displayPane.screenToGrid(x, y, 0);
            InputEvent mevent = new InputEvent(EventType.MOUSE_WHEEL,
                    coords[0], coords[1], event.count);
            sendEvent(mevent);
        }
        return !leader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseExit(org.eclipse
     * .swt.widgets.Event)
     */
    @Override
    public boolean handleMouseExit(Event event) {
        // TODO doesn't do anything right now to reduce bandwidth
        return !isSessionLeader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.raytheon.uf.viz.core.rsc.IInputHandler#handleMouseEnter(org.eclipse
     * .swt.widgets.Event)
     */
    @Override
    public boolean handleMouseEnter(Event event) {
        // TODO doesn't do anything right now to reduce bandwidth
        return !isSessionLeader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleKeyDown(int)
     */
    @Override
    public boolean handleKeyDown(int keyCode) {
        boolean leader = isSessionLeader();
        if (leader) {
            InputEvent event = new InputEvent(EventType.KEY_DOWN, -1, -1,
                    keyCode);
            sendEvent(event);
        }
        return !leader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.raytheon.uf.viz.core.rsc.IInputHandler#handleKeyUp(int)
     */
    @Override
    public boolean handleKeyUp(int keyCode) {
        boolean leader = isSessionLeader();
        if (leader) {
            InputEvent event = new InputEvent(EventType.KEY_DOWN, -1, -1,
                    keyCode);
            sendEvent(event);
        }
        return !leader;
    }

}
