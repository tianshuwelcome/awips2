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

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * RwparamsId generated by hbm2java
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Oct 17, 2008                        Initial generation by hbm2java
 * Aug 19, 2011      10672     jkorman Move refactor to new project
 * Oct 07, 2013       2361     njensen Removed XML annotations
 * Aug 30, 2016       5631     bkowal  Cleanup.
 * 
 * </pre>
 * 
 * @author jkorman
 */
@Embeddable
@DynamicSerialize
public class RwparamsId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final int DOUBLE_HEAP_SORTING_SEARCH_IND = 1;
    
    public static final String DOUBLE_HEAP_SORTING_SEARCH = "double heap-sorting";
    
    public static final String SPIRAL_SEARCH = "spiral search";

    @DynamicSerializeElement
    private Float rwMinRain;

    @DynamicSerializeElement
    private Float rwSepDist;

    @DynamicSerializeElement
    private Float rwLag0IndCorr;

    @DynamicSerializeElement
    private Float rwLag0CondCorr;

    @DynamicSerializeElement
    private Short numNearGages;

    @DynamicSerializeElement
    private Short numNearRadBins;

    @DynamicSerializeElement
    private Float defCondVarRad;

    @DynamicSerializeElement
    private Float defIndCorrScl;

    @DynamicSerializeElement
    private Float defCondCorrScl;

    @DynamicSerializeElement
    private Float minIndCorrScl;

    @DynamicSerializeElement
    private Float minCondCorrScl;

    @DynamicSerializeElement
    private Float maxIndCorrScl;

    @DynamicSerializeElement
    private Float maxCondCorrScl;

    /**
     * 1 = double heap-sorting; anything else = spiral search
     */
    @DynamicSerializeElement
    private Short nnSrchMethod;

    public RwparamsId() {
    }

    public RwparamsId(Float rwMinRain, Float rwSepDist, Float rwLag0IndCorr,
            Float rwLag0CondCorr, Short numNearGages, Short numNearRadBins,
            Float defCondVarRad, Float defIndCorrScl, Float defCondCorrScl,
            Float minIndCorrScl, Float minCondCorrScl, Float maxIndCorrScl,
            Float maxCondCorrScl, Short nnSrchMethod) {
        this.rwMinRain = rwMinRain;
        this.rwSepDist = rwSepDist;
        this.rwLag0IndCorr = rwLag0IndCorr;
        this.rwLag0CondCorr = rwLag0CondCorr;
        this.numNearGages = numNearGages;
        this.numNearRadBins = numNearRadBins;
        this.defCondVarRad = defCondVarRad;
        this.defIndCorrScl = defIndCorrScl;
        this.defCondCorrScl = defCondCorrScl;
        this.minIndCorrScl = minIndCorrScl;
        this.minCondCorrScl = minCondCorrScl;
        this.maxIndCorrScl = maxIndCorrScl;
        this.maxCondCorrScl = maxCondCorrScl;
        this.nnSrchMethod = nnSrchMethod;
    }

    @Column(name = "rw_min_rain", precision = 8, scale = 8)
    public Float getRwMinRain() {
        return this.rwMinRain;
    }

    public void setRwMinRain(Float rwMinRain) {
        this.rwMinRain = rwMinRain;
    }

    @Column(name = "rw_sep_dist", precision = 8, scale = 8)
    public Float getRwSepDist() {
        return this.rwSepDist;
    }

    public void setRwSepDist(Float rwSepDist) {
        this.rwSepDist = rwSepDist;
    }

    @Column(name = "rw_lag0_ind_corr", precision = 8, scale = 8)
    public Float getRwLag0IndCorr() {
        return this.rwLag0IndCorr;
    }

    public void setRwLag0IndCorr(Float rwLag0IndCorr) {
        this.rwLag0IndCorr = rwLag0IndCorr;
    }

    @Column(name = "rw_lag0_cond_corr", precision = 8, scale = 8)
    public Float getRwLag0CondCorr() {
        return this.rwLag0CondCorr;
    }

    public void setRwLag0CondCorr(Float rwLag0CondCorr) {
        this.rwLag0CondCorr = rwLag0CondCorr;
    }

    @Column(name = "num_near_gages")
    public Short getNumNearGages() {
        return this.numNearGages;
    }

    public void setNumNearGages(Short numNearGages) {
        this.numNearGages = numNearGages;
    }

    @Column(name = "num_near_rad_bins")
    public Short getNumNearRadBins() {
        return this.numNearRadBins;
    }

    public void setNumNearRadBins(Short numNearRadBins) {
        this.numNearRadBins = numNearRadBins;
    }

    @Column(name = "def_cond_var_rad", precision = 8, scale = 8)
    public Float getDefCondVarRad() {
        return this.defCondVarRad;
    }

    public void setDefCondVarRad(Float defCondVarRad) {
        this.defCondVarRad = defCondVarRad;
    }

    @Column(name = "def_ind_corr_scl", precision = 8, scale = 8)
    public Float getDefIndCorrScl() {
        return this.defIndCorrScl;
    }

    public void setDefIndCorrScl(Float defIndCorrScl) {
        this.defIndCorrScl = defIndCorrScl;
    }

    @Column(name = "def_cond_corr_scl", precision = 8, scale = 8)
    public Float getDefCondCorrScl() {
        return this.defCondCorrScl;
    }

    public void setDefCondCorrScl(Float defCondCorrScl) {
        this.defCondCorrScl = defCondCorrScl;
    }

    @Column(name = "min_ind_corr_scl", precision = 8, scale = 8)
    public Float getMinIndCorrScl() {
        return this.minIndCorrScl;
    }

    public void setMinIndCorrScl(Float minIndCorrScl) {
        this.minIndCorrScl = minIndCorrScl;
    }

    @Column(name = "min_cond_corr_scl", precision = 8, scale = 8)
    public Float getMinCondCorrScl() {
        return this.minCondCorrScl;
    }

    public void setMinCondCorrScl(Float minCondCorrScl) {
        this.minCondCorrScl = minCondCorrScl;
    }

    @Column(name = "max_ind_corr_scl", precision = 8, scale = 8)
    public Float getMaxIndCorrScl() {
        return this.maxIndCorrScl;
    }

    public void setMaxIndCorrScl(Float maxIndCorrScl) {
        this.maxIndCorrScl = maxIndCorrScl;
    }

    @Column(name = "max_cond_corr_scl", precision = 8, scale = 8)
    public Float getMaxCondCorrScl() {
        return this.maxCondCorrScl;
    }

    public void setMaxCondCorrScl(Float maxCondCorrScl) {
        this.maxCondCorrScl = maxCondCorrScl;
    }

    @Column(name = "nn_srch_method")
    public Short getNnSrchMethod() {
        return this.nnSrchMethod;
    }

    public void setNnSrchMethod(Short nnSrchMethod) {
        this.nnSrchMethod = nnSrchMethod;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof RwparamsId))
            return false;
        RwparamsId castOther = (RwparamsId) other;

        return ((this.getRwMinRain() == castOther.getRwMinRain()) || (this
                .getRwMinRain() != null && castOther.getRwMinRain() != null && this
                .getRwMinRain().equals(castOther.getRwMinRain())))
                && ((this.getRwSepDist() == castOther.getRwSepDist()) || (this
                        .getRwSepDist() != null
                        && castOther.getRwSepDist() != null && this
                        .getRwSepDist().equals(castOther.getRwSepDist())))
                && ((this.getRwLag0IndCorr() == castOther.getRwLag0IndCorr()) || (this
                        .getRwLag0IndCorr() != null
                        && castOther.getRwLag0IndCorr() != null && this
                        .getRwLag0IndCorr()
                        .equals(castOther.getRwLag0IndCorr())))
                && ((this.getRwLag0CondCorr() == castOther.getRwLag0CondCorr()) || (this
                        .getRwLag0CondCorr() != null
                        && castOther.getRwLag0CondCorr() != null && this
                        .getRwLag0CondCorr().equals(
                                castOther.getRwLag0CondCorr())))
                && ((this.getNumNearGages() == castOther.getNumNearGages()) || (this
                        .getNumNearGages() != null
                        && castOther.getNumNearGages() != null && this
                        .getNumNearGages().equals(castOther.getNumNearGages())))
                && ((this.getNumNearRadBins() == castOther.getNumNearRadBins()) || (this
                        .getNumNearRadBins() != null
                        && castOther.getNumNearRadBins() != null && this
                        .getNumNearRadBins().equals(
                                castOther.getNumNearRadBins())))
                && ((this.getDefCondVarRad() == castOther.getDefCondVarRad()) || (this
                        .getDefCondVarRad() != null
                        && castOther.getDefCondVarRad() != null && this
                        .getDefCondVarRad()
                        .equals(castOther.getDefCondVarRad())))
                && ((this.getDefIndCorrScl() == castOther.getDefIndCorrScl()) || (this
                        .getDefIndCorrScl() != null
                        && castOther.getDefIndCorrScl() != null && this
                        .getDefIndCorrScl()
                        .equals(castOther.getDefIndCorrScl())))
                && ((this.getDefCondCorrScl() == castOther.getDefCondCorrScl()) || (this
                        .getDefCondCorrScl() != null
                        && castOther.getDefCondCorrScl() != null && this
                        .getDefCondCorrScl().equals(
                                castOther.getDefCondCorrScl())))
                && ((this.getMinIndCorrScl() == castOther.getMinIndCorrScl()) || (this
                        .getMinIndCorrScl() != null
                        && castOther.getMinIndCorrScl() != null && this
                        .getMinIndCorrScl()
                        .equals(castOther.getMinIndCorrScl())))
                && ((this.getMinCondCorrScl() == castOther.getMinCondCorrScl()) || (this
                        .getMinCondCorrScl() != null
                        && castOther.getMinCondCorrScl() != null && this
                        .getMinCondCorrScl().equals(
                                castOther.getMinCondCorrScl())))
                && ((this.getMaxIndCorrScl() == castOther.getMaxIndCorrScl()) || (this
                        .getMaxIndCorrScl() != null
                        && castOther.getMaxIndCorrScl() != null && this
                        .getMaxIndCorrScl()
                        .equals(castOther.getMaxIndCorrScl())))
                && ((this.getMaxCondCorrScl() == castOther.getMaxCondCorrScl()) || (this
                        .getMaxCondCorrScl() != null
                        && castOther.getMaxCondCorrScl() != null && this
                        .getMaxCondCorrScl().equals(
                                castOther.getMaxCondCorrScl())))
                && ((this.getNnSrchMethod() == castOther.getNnSrchMethod()) || (this
                        .getNnSrchMethod() != null
                        && castOther.getNnSrchMethod() != null && this
                        .getNnSrchMethod().equals(castOther.getNnSrchMethod())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getRwMinRain() == null ? 0 : this.getRwMinRain().hashCode());
        result = 37 * result
                + (getRwSepDist() == null ? 0 : this.getRwSepDist().hashCode());
        result = 37
                * result
                + (getRwLag0IndCorr() == null ? 0 : this.getRwLag0IndCorr()
                        .hashCode());
        result = 37
                * result
                + (getRwLag0CondCorr() == null ? 0 : this.getRwLag0CondCorr()
                        .hashCode());
        result = 37
                * result
                + (getNumNearGages() == null ? 0 : this.getNumNearGages()
                        .hashCode());
        result = 37
                * result
                + (getNumNearRadBins() == null ? 0 : this.getNumNearRadBins()
                        .hashCode());
        result = 37
                * result
                + (getDefCondVarRad() == null ? 0 : this.getDefCondVarRad()
                        .hashCode());
        result = 37
                * result
                + (getDefIndCorrScl() == null ? 0 : this.getDefIndCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getDefCondCorrScl() == null ? 0 : this.getDefCondCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getMinIndCorrScl() == null ? 0 : this.getMinIndCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getMinCondCorrScl() == null ? 0 : this.getMinCondCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getMaxIndCorrScl() == null ? 0 : this.getMaxIndCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getMaxCondCorrScl() == null ? 0 : this.getMaxCondCorrScl()
                        .hashCode());
        result = 37
                * result
                + (getNnSrchMethod() == null ? 0 : this.getNnSrchMethod()
                        .hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RwparamsId [");
        sb.append("rwMinRain=").append(rwMinRain);
        sb.append(", rwSepDist=").append(rwSepDist);
        sb.append(", rwLag0IndCorr=").append(rwLag0IndCorr);
        sb.append(", rwLag0CondCorr=").append(rwLag0CondCorr);
        sb.append(", numNearGages=").append(numNearGages);
        sb.append(", numNearRadBins=").append(numNearRadBins);
        sb.append(", defCondVarRad=").append(defCondVarRad);
        sb.append(", defIndCorrScl=").append(defIndCorrScl);
        sb.append(", defCondCorrScl=").append(defCondCorrScl);
        sb.append(", minIndCorrScl=").append(minIndCorrScl);
        sb.append(", minCondCorrScl=").append(minCondCorrScl);
        sb.append(", maxIndCorrScl=").append(maxIndCorrScl);
        sb.append(", maxCondCorrScl=").append(maxCondCorrScl);
        sb.append(", nnSrchMethod=").append(nnSrchMethod);
        sb.append("]");
        return sb.toString();
    }
}
