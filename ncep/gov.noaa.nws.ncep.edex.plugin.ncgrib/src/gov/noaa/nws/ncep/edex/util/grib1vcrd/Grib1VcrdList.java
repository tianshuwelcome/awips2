/**
 * This file was generated by the JavaTM Architecture for XML Binding(JAXB) 
 * Reference Implementation, vJAXB 2.1.10 in JDK 6 
 * See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
 * any modifications to this file will be lost upon recompilation of the source schema
 * Generated on: 2010.09.10 at 11:48:39 AM EDT 
 *
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * 10/2010  	276		   L. Lin      Initial creation
 * 
 * </pre>
 * 
 * This code has been developed by the SIB for use in the AWIPS2 system.
 * @author L. Lin
 * @version 1.0
 */

package gov.noaa.nws.ncep.edex.util.grib1vcrd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.raytheon.uf.common.serialization.ISerializableObject;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "grib1Vcrd"
})
@XmlRootElement(name = "grib1vcrdList")
public class Grib1VcrdList implements ISerializableObject {

    @XmlElement(name = "grib1vcrd")
    protected List<Grib1Vcrd> grib1Vcrd;

    /**
     * Gets the value of the grib1Vcrd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grib1Vcrd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrib1Vcrd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Grib1Vcrd }
     * 
     * 
     */
    public List<Grib1Vcrd> getGrib1Vcrd() {
        if (grib1Vcrd == null) {
            grib1Vcrd = new ArrayList<Grib1Vcrd>();
        }
        return this.grib1Vcrd;
    }

}
