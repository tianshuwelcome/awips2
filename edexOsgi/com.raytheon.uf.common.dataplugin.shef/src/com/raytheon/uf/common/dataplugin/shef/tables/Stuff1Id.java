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
// default package
// Generated Oct 17, 2008 2:22:17 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Stuff1Id generated by hbm2java
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
 * 
 * </pre>
 * 
 * @author jkorman
 * @version 1.1
 */
@Embeddable
@com.raytheon.uf.common.serialization.annotations.DynamicSerialize
public class Stuff1Id extends com.raytheon.uf.common.dataplugin.persist.PersistableDataObject implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private String lid;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private Date basistime;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private Date validtime;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private Double value;

    public Stuff1Id() {
    }

    public Stuff1Id(String lid, Date basistime, Date validtime, Double value) {
        this.lid = lid;
        this.basistime = basistime;
        this.validtime = validtime;
        this.value = value;
    }

    @Column(name = "lid", length = 8)
    public String getLid() {
        return this.lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Column(name = "basistime", length = 29)
    public Date getBasistime() {
        return this.basistime;
    }

    public void setBasistime(Date basistime) {
        this.basistime = basistime;
    }

    @Column(name = "validtime", length = 29)
    public Date getValidtime() {
        return this.validtime;
    }

    public void setValidtime(Date validtime) {
        this.validtime = validtime;
    }

    @Column(name = "value", precision = 17, scale = 17)
    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof Stuff1Id))
            return false;
        Stuff1Id castOther = (Stuff1Id) other;

        return ((this.getLid() == castOther.getLid()) || (this.getLid() != null
                && castOther.getLid() != null && this.getLid().equals(
                castOther.getLid())))
                && ((this.getBasistime() == castOther.getBasistime()) || (this
                        .getBasistime() != null
                        && castOther.getBasistime() != null && this
                        .getBasistime().equals(castOther.getBasistime())))
                && ((this.getValidtime() == castOther.getValidtime()) || (this
                        .getValidtime() != null
                        && castOther.getValidtime() != null && this
                        .getValidtime().equals(castOther.getValidtime())))
                && ((this.getValue() == castOther.getValue()) || (this
                        .getValue() != null
                        && castOther.getValue() != null && this.getValue()
                        .equals(castOther.getValue())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getLid() == null ? 0 : this.getLid().hashCode());
        result = 37 * result
                + (getBasistime() == null ? 0 : this.getBasistime().hashCode());
        result = 37 * result
                + (getValidtime() == null ? 0 : this.getValidtime().hashCode());
        result = 37 * result
                + (getValue() == null ? 0 : this.getValue().hashCode());
        return result;
    }

}
