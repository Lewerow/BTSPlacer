
package serialization.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basebandResourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basebandResourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capacity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basebandResourceType", propOrder = {
    "capacity"
})
public class JaxbBasebandResourceType {

    protected double capacity;

    /**
     * Gets the value of the capacity property.
     * 
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     */
    public void setCapacity(double value) {
        this.capacity = value;
    }

}
