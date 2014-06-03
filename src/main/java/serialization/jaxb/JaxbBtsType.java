
package serialization.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for btsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="btsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cellType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="locationY" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;sequence>
 *           &lt;element name="radioResource" type="{}radioResourceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="basebandResource" type="{}basebandResourceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "btsType", propOrder = {
    "cellType",
    "locationX",
    "locationY",
    "radioResource",
    "basebandResource"
})
public class JaxbBtsType {

    @XmlElement(required = true)
    protected String cellType;
    protected double locationX;
    protected double locationY;
    protected List<JaxbRadioResourceType> radioResource;
    protected List<JaxbBasebandResourceType> basebandResource;

    /**
     * Gets the value of the cellType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellType() {
        return cellType;
    }

    /**
     * Sets the value of the cellType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellType(String value) {
        this.cellType = value;
    }

    /**
     * Gets the value of the locationX property.
     * 
     */
    public double getLocationX() {
        return locationX;
    }

    /**
     * Sets the value of the locationX property.
     * 
     */
    public void setLocationX(double value) {
        this.locationX = value;
    }

    /**
     * Gets the value of the locationY property.
     * 
     */
    public double getLocationY() {
        return locationY;
    }

    /**
     * Sets the value of the locationY property.
     * 
     */
    public void setLocationY(double value) {
        this.locationY = value;
    }

    /**
     * Gets the value of the radioResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the radioResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRadioResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JaxbRadioResourceType }
     * 
     * 
     */
    public List<JaxbRadioResourceType> getRadioResource() {
        if (radioResource == null) {
            radioResource = new ArrayList<JaxbRadioResourceType>();
        }
        return this.radioResource;
    }

    /**
     * Gets the value of the basebandResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basebandResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasebandResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JaxbBasebandResourceType }
     * 
     * 
     */
    public List<JaxbBasebandResourceType> getBasebandResource() {
        if (basebandResource == null) {
            basebandResource = new ArrayList<JaxbBasebandResourceType>();
        }
        return this.basebandResource;
    }

}
