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

import java.io.Serializable;

// default package
// Generated Oct 17, 2008 2:22:17 PM by Hibernate Tools 3.2.2.GA

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Hourlypc generated by hbm2java
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
 * Sep 13, 2016  5631      bkowal      Minor cleanup.
 * 
 * </pre>
 * 
 * @author jkorman
 */
@NamedQueries({
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_EQ_LID_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_EQ_LID_OBSTIME__HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_NOT_LID_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_NOT_LID_OBSTIME__HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_EQ_LID_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_EQ_LID_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_NOT_LID_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_NOT_LID_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_EQ_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_EQ_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_NOT_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_SNGL_NOT_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_EQ_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_EQ_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_NOT_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_TS_MULTI_NOT_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_LID_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_LID_OBSTIME_HQL),
        @NamedQuery(name = Hourlypc.SELECT_HOURLYPC_FOR_OBSTIME, query = Hourlypc.SELECT_HOURLYPC_FOR_OBSTIME_HQL) })
@Entity
@Table(name = "hourlypc")
@com.raytheon.uf.common.serialization.annotations.DynamicSerialize
public class Hourlypc extends PersistableDataObject<HourlypcId>
        implements Serializable, IHourlyTS {

    // ------------Select records for ts, lid, between start and finish

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_EQ_LID_OBSTIME = "SELECT_HOURLYPC_FOR_TS_SNGL_EQ_LID_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_EQ_LID_OBSTIME__HQL = "FROM Hourlypc hpc WHERE hpc.id.ts = :ts AND hpc.id.lid = :lid AND hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_NOT_LID_OBSTIME = "SELECT_HOURLYPC_FOR_TS_SNGL_NOT_LID_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_NOT_LID_OBSTIME__HQL = "FROM Hourlypc hpc WHERE hpc.id.ts != :ts AND hpc.id.lid = :lid AND hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_EQ_LID_OBSTIME = "SELECT_HOURLYPC_FOR_TS_MULTI_EQ_LID_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_EQ_LID_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts in :ts AND hpc.id.lid = :lid AND hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_NOT_LID_OBSTIME = "SELECT_HOURLYPC_FOR_TS_MULTI_NOT_LID_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_NOT_LID_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts not in :ts AND hpc.id.lid = :lid AND hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.ts ASC, hpc.id.obsdate ASC";

    // ------------Select records for ts, between start and finish

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_EQ_OBSTIME = "SELECT_HOURLYPC_FOR_TS_SNGL_EQ_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_EQ_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts = :ts AND hpc.id.obsdate >= :start AND hpc.id.obsdate <= :finish ORDER BY hpc.id.lid ASC, hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_NOT_OBSTIME = "SELECT_HOURLYPC_FOR_TS_SNGL_NOT_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_SNGL_NOT_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts != :ts AND hpc.id.obsdate >= :start AND hpc.id.obsdate <= :finish ORDER BY hpc.id.lid ASC, hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_EQ_OBSTIME = "SELECT_HOURLYPC_FOR_TS_MULTI_EQ_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_EQ_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts in :ts AND hpc.id.obsdate >= :start AND hpc.id.obsdate <= :finish ORDER BY hpc.id.lid ASC, hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_NOT_OBSTIME = "SELECT_HOURLYPC_FOR_TS_MULTI_NOT_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_TS_MULTI_NOT_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.ts not in :ts AND hpc.id.obsdate >= :start AND hpc.id.obsdate <= :finish ORDER BY hpc.id.lid ASC, hpc.id.ts ASC, hpc.id.obsdate ASC";

    // ------------Select records for lid, between start and finish

    public static final String SELECT_HOURLYPC_FOR_LID_OBSTIME = "SELECT_HOURLYPC_FOR_LID_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_LID_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.lid = lid AND hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.ts ASC, hpc.id.obsdate ASC";

    public static final String SELECT_HOURLYPC_FOR_OBSTIME = "SELECT_HOURLYPC_FOR_OBSTIME";

    public static final String SELECT_HOURLYPC_FOR_OBSTIME_HQL = "FROM Hourlypc hpc WHERE hpc.id.obsdate >= :start AND obsdate <= :finish ORDER BY hpc.id.lid ASC, hpc.id.ts ASC, hpc.id.obsdate ASC";

    private static final long serialVersionUID = 1L;

    @DynamicSerializeElement
    private HourlypcId id;

    @DynamicSerializeElement
    private String minuteOffset;

    @DynamicSerializeElement
    private String hourlyQc;

    @DynamicSerializeElement
    private Short hour1;

    @DynamicSerializeElement
    private Short hour2;

    @DynamicSerializeElement
    private Short hour3;

    @DynamicSerializeElement
    private Short hour4;

    @DynamicSerializeElement
    private Short hour5;

    @DynamicSerializeElement
    private Short hour6;

    @DynamicSerializeElement
    private Short hour7;

    @DynamicSerializeElement
    private Short hour8;

    @DynamicSerializeElement
    private Short hour9;

    @DynamicSerializeElement
    private Short hour10;

    @DynamicSerializeElement
    private Short hour11;

    @DynamicSerializeElement
    private Short hour12;

    @DynamicSerializeElement
    private Short hour13;

    @DynamicSerializeElement
    private Short hour14;

    @DynamicSerializeElement
    private Short hour15;

    @DynamicSerializeElement
    private Short hour16;

    @DynamicSerializeElement
    private Short hour17;

    @DynamicSerializeElement
    private Short hour18;

    @DynamicSerializeElement
    private Short hour19;

    @DynamicSerializeElement
    private Short hour20;

    @DynamicSerializeElement
    private Short hour21;

    @DynamicSerializeElement
    private Short hour22;

    @DynamicSerializeElement
    private Short hour23;

    @DynamicSerializeElement
    private Short hour24;

    public Hourlypc() {
    }

    public Hourlypc(HourlypcId id) {
        this.id = id;
    }

    public Hourlypc(HourlypcId id, String minuteOffset, String hourlyQc,
            Short hour1, Short hour2, Short hour3, Short hour4, Short hour5,
            Short hour6, Short hour7, Short hour8, Short hour9, Short hour10,
            Short hour11, Short hour12, Short hour13, Short hour14,
            Short hour15, Short hour16, Short hour17, Short hour18,
            Short hour19, Short hour20, Short hour21, Short hour22,
            Short hour23, Short hour24) {
        this.id = id;
        this.minuteOffset = minuteOffset;
        this.hourlyQc = hourlyQc;
        this.hour1 = hour1;
        this.hour2 = hour2;
        this.hour3 = hour3;
        this.hour4 = hour4;
        this.hour5 = hour5;
        this.hour6 = hour6;
        this.hour7 = hour7;
        this.hour8 = hour8;
        this.hour9 = hour9;
        this.hour10 = hour10;
        this.hour11 = hour11;
        this.hour12 = hour12;
        this.hour13 = hour13;
        this.hour14 = hour14;
        this.hour15 = hour15;
        this.hour16 = hour16;
        this.hour17 = hour17;
        this.hour18 = hour18;
        this.hour19 = hour19;
        this.hour20 = hour20;
        this.hour21 = hour21;
        this.hour22 = hour22;
        this.hour23 = hour23;
        this.hour24 = hour24;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "lid", column = @Column(name = "lid", nullable = false, length = 8)),
            @AttributeOverride(name = "ts", column = @Column(name = "ts", nullable = false, length = 2)),
            @AttributeOverride(name = "obsdate", column = @Column(name = "obsdate", nullable = false, length = 13)) })
    public HourlypcId getId() {
        return this.id;
    }

    public void setId(HourlypcId id) {
        this.id = id;
    }

    @Column(name = "minute_offset", length = 24)
    public String getMinuteOffset() {
        return this.minuteOffset;
    }

    public void setMinuteOffset(String minuteOffset) {
        this.minuteOffset = minuteOffset;
    }

    @Column(name = "hourly_qc", length = 24)
    public String getHourlyQc() {
        return this.hourlyQc;
    }

    public void setHourlyQc(String hourlyQc) {
        this.hourlyQc = hourlyQc;
    }

    @Column(name = "hour1")
    public Short getHour1() {
        return this.hour1;
    }

    public void setHour1(Short hour1) {
        this.hour1 = hour1;
    }

    @Column(name = "hour2")
    public Short getHour2() {
        return this.hour2;
    }

    public void setHour2(Short hour2) {
        this.hour2 = hour2;
    }

    @Column(name = "hour3")
    public Short getHour3() {
        return this.hour3;
    }

    public void setHour3(Short hour3) {
        this.hour3 = hour3;
    }

    @Column(name = "hour4")
    public Short getHour4() {
        return this.hour4;
    }

    public void setHour4(Short hour4) {
        this.hour4 = hour4;
    }

    @Column(name = "hour5")
    public Short getHour5() {
        return this.hour5;
    }

    public void setHour5(Short hour5) {
        this.hour5 = hour5;
    }

    @Column(name = "hour6")
    public Short getHour6() {
        return this.hour6;
    }

    public void setHour6(Short hour6) {
        this.hour6 = hour6;
    }

    @Column(name = "hour7")
    public Short getHour7() {
        return this.hour7;
    }

    public void setHour7(Short hour7) {
        this.hour7 = hour7;
    }

    @Column(name = "hour8")
    public Short getHour8() {
        return this.hour8;
    }

    public void setHour8(Short hour8) {
        this.hour8 = hour8;
    }

    @Column(name = "hour9")
    public Short getHour9() {
        return this.hour9;
    }

    public void setHour9(Short hour9) {
        this.hour9 = hour9;
    }

    @Column(name = "hour10")
    public Short getHour10() {
        return this.hour10;
    }

    public void setHour10(Short hour10) {
        this.hour10 = hour10;
    }

    @Column(name = "hour11")
    public Short getHour11() {
        return this.hour11;
    }

    public void setHour11(Short hour11) {
        this.hour11 = hour11;
    }

    @Column(name = "hour12")
    public Short getHour12() {
        return this.hour12;
    }

    public void setHour12(Short hour12) {
        this.hour12 = hour12;
    }

    @Column(name = "hour13")
    public Short getHour13() {
        return this.hour13;
    }

    public void setHour13(Short hour13) {
        this.hour13 = hour13;
    }

    @Column(name = "hour14")
    public Short getHour14() {
        return this.hour14;
    }

    public void setHour14(Short hour14) {
        this.hour14 = hour14;
    }

    @Column(name = "hour15")
    public Short getHour15() {
        return this.hour15;
    }

    public void setHour15(Short hour15) {
        this.hour15 = hour15;
    }

    @Column(name = "hour16")
    public Short getHour16() {
        return this.hour16;
    }

    public void setHour16(Short hour16) {
        this.hour16 = hour16;
    }

    @Column(name = "hour17")
    public Short getHour17() {
        return this.hour17;
    }

    public void setHour17(Short hour17) {
        this.hour17 = hour17;
    }

    @Column(name = "hour18")
    public Short getHour18() {
        return this.hour18;
    }

    public void setHour18(Short hour18) {
        this.hour18 = hour18;
    }

    @Column(name = "hour19")
    public Short getHour19() {
        return this.hour19;
    }

    public void setHour19(Short hour19) {
        this.hour19 = hour19;
    }

    @Column(name = "hour20")
    public Short getHour20() {
        return this.hour20;
    }

    public void setHour20(Short hour20) {
        this.hour20 = hour20;
    }

    @Column(name = "hour21")
    public Short getHour21() {
        return this.hour21;
    }

    public void setHour21(Short hour21) {
        this.hour21 = hour21;
    }

    @Column(name = "hour22")
    public Short getHour22() {
        return this.hour22;
    }

    public void setHour22(Short hour22) {
        this.hour22 = hour22;
    }

    @Column(name = "hour23")
    public Short getHour23() {
        return this.hour23;
    }

    public void setHour23(Short hour23) {
        this.hour23 = hour23;
    }

    @Column(name = "hour24")
    public Short getHour24() {
        return this.hour24;
    }

    public void setHour24(Short hour24) {
        this.hour24 = hour24;
    }

    @Override
    @Transient
    public String getLid() {
        return getId().getLid();
    }

    @Override
    @Transient
    public String getTs() {
        return getId().getTs();
    }

}
