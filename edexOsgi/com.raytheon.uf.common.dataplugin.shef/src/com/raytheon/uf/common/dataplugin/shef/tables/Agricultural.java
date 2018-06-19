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
package com.raytheon.uf.common.dataplugin.shef.tables;

// Generated Oct 17, 2008 2:22:17 PM by Hibernate Tools 3.2.2.GA

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.dataplugin.shef.data.ObsHqlConstants;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Agricultural generated by hbm2java
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Oct 17, 2008                        Initial generation by hbm2java
 * Aug 19, 2011      10672     jkorman Move refactor to new project
 * Oct 07, 2013       2361     njensen Removed XML annotations
 * May 23, 2016       5590     bkowal  Cleanup.
 * Jun 24, 2016       5699     bkowal  Defined the observation retrieval named queries.
 * Dec 18, 2017       6554     bkowal  Implemented {@link ICheckValue}.
 * 
 * </pre>
 * 
 * @author jkorman
 */
@NamedQueries({
        @NamedQuery(name = Agricultural.SELECT_OBS, query = Agricultural.SELECT_OBS_HQL),
        @NamedQuery(name = Agricultural.SELECT_OBS_BY_LID, query = Agricultural.SELECT_OBS_BY_LID_HQL),
        @NamedQuery(name = Agricultural.SELECT_OBS_BY_PE, query = Agricultural.SELECT_OBS_BY_PE_HQL),
        @NamedQuery(name = Agricultural.SELECT_OBS_BY_LID_AND_PE, query = Agricultural.SELECT_OBS_BY_LID_AND_PE_HQL) })
@Entity
@Table(name = "agricultural")
@DynamicSerialize
public class Agricultural extends PersistableDataObject<AgriculturalId>
        implements Serializable, ICheckValue {

    private static final String ORDER_BY_HQL = " ORDER BY o.id.lid, o.id.pe, o.id.obstime ASC";

    public static final String SELECT_OBS = "selectAgriculturalObs";

    public static final String SELECT_OBS_BY_LID = "selectAgriculturalObsByLid";

    public static final String SELECT_OBS_BY_PE = "selectAgriculturalObsByPE";

    public static final String SELECT_OBS_BY_LID_AND_PE = "selectAgriculturalObsByLidAndPE";

    protected static final String SELECT_OBS_HQL = "FROM Agricultural o WHERE o.id.obstime >= "
            + ObsHqlConstants.HQL_START_OBS_TIME_PARAM
            + " AND o.id.obstime <= "
            + ObsHqlConstants.HQL_END_OBS_TIME_PARAM
            + " AND o.qualityCode >= "
            + ObsHqlConstants.HQL_QUALITY_CODE_PARAM
            + " AND o.value != "
            + ObsHqlConstants.HQL_VALUE_PARAM
            + ORDER_BY_HQL;

    protected static final String SELECT_OBS_BY_LID_HQL = "FROM Agricultural o WHERE o.id.obstime >= "
            + ObsHqlConstants.HQL_START_OBS_TIME_PARAM
            + " AND o.id.obstime <= "
            + ObsHqlConstants.HQL_END_OBS_TIME_PARAM
            + " AND o.qualityCode >= "
            + ObsHqlConstants.HQL_QUALITY_CODE_PARAM
            + " AND o.value != "
            + ObsHqlConstants.HQL_VALUE_PARAM
            + " AND o.id.lid IN "
            + ObsHqlConstants.HQL_LID_PARAM
            + ORDER_BY_HQL;

    protected static final String SELECT_OBS_BY_PE_HQL = "FROM Agricultural o WHERE o.id.obstime >= "
            + ObsHqlConstants.HQL_START_OBS_TIME_PARAM
            + " AND o.id.obstime <= "
            + ObsHqlConstants.HQL_END_OBS_TIME_PARAM
            + " AND o.qualityCode >= "
            + ObsHqlConstants.HQL_QUALITY_CODE_PARAM
            + " AND o.value != "
            + ObsHqlConstants.HQL_VALUE_PARAM
            + " AND o.id.pe IN " + ObsHqlConstants.HQL_PE_PARAM + ORDER_BY_HQL;

    protected static final String SELECT_OBS_BY_LID_AND_PE_HQL = "FROM Agricultural o WHERE o.id.obstime >= "
            + ObsHqlConstants.HQL_START_OBS_TIME_PARAM
            + " AND o.id.obstime <= "
            + ObsHqlConstants.HQL_END_OBS_TIME_PARAM
            + " AND o.qualityCode >= "
            + ObsHqlConstants.HQL_QUALITY_CODE_PARAM
            + " AND o.value != "
            + ObsHqlConstants.HQL_VALUE_PARAM
            + " AND o.id.lid IN "
            + ObsHqlConstants.HQL_LID_PARAM
            + " AND o.id.pe IN " + ObsHqlConstants.HQL_PE_PARAM + ORDER_BY_HQL;

    private static final long serialVersionUID = 1L;

    @DynamicSerializeElement
    private AgriculturalId id;

    @DynamicSerializeElement
    private Double value;

    @DynamicSerializeElement
    private String shefQualCode;

    @DynamicSerializeElement
    private Integer qualityCode;

    @DynamicSerializeElement
    private Short revision;

    @DynamicSerializeElement
    private String productId;

    @DynamicSerializeElement
    private Date producttime;

    @DynamicSerializeElement
    private Date postingtime;

    public Agricultural() {
    }

    public Agricultural(AgriculturalId id) {
        this.id = id;
    }

    public Agricultural(AgriculturalId id, Double value, String shefQualCode,
            Integer qualityCode, Short revision, String productId,
            Date producttime, Date postingtime) {
        this.id = id;
        this.value = value;
        this.shefQualCode = shefQualCode;
        this.qualityCode = qualityCode;
        this.revision = revision;
        this.productId = productId;
        this.producttime = producttime;
        this.postingtime = postingtime;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "lid", column = @Column(name = "lid", nullable = false, length = 8)),
            @AttributeOverride(name = "pe", column = @Column(name = "pe", nullable = false, length = 2)),
            @AttributeOverride(name = "dur", column = @Column(name = "dur", nullable = false)),
            @AttributeOverride(name = "ts", column = @Column(name = "ts", nullable = false, length = 2)),
            @AttributeOverride(name = "extremum", column = @Column(name = "extremum", nullable = false, length = 1)),
            @AttributeOverride(name = "obstime", column = @Column(name = "obstime", nullable = false, length = 29)) })
    public AgriculturalId getId() {
        return this.id;
    }

    public void setId(AgriculturalId id) {
        this.id = id;
    }

    @Column(name = "value", precision = 17, scale = 17)
    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "shef_qual_code", length = 1)
    public String getShefQualCode() {
        return this.shefQualCode;
    }

    public void setShefQualCode(String shefQualCode) {
        this.shefQualCode = shefQualCode;
    }

    @Column(name = "quality_code")
    public Integer getQualityCode() {
        return this.qualityCode;
    }

    public void setQualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
    }

    @Column(name = "revision")
    public Short getRevision() {
        return this.revision;
    }

    public void setRevision(Short revision) {
        this.revision = revision;
    }

    @Column(name = "product_id", length = 10)
    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "producttime", length = 29)
    public Date getProducttime() {
        return this.producttime;
    }

    public void setProducttime(Date producttime) {
        this.producttime = producttime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postingtime", length = 29)
    public Date getPostingtime() {
        return this.postingtime;
    }

    public void setPostingtime(Date postingtime) {
        this.postingtime = postingtime;
    }

    @Transient
    @Override
    public String getCompareValue() {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}