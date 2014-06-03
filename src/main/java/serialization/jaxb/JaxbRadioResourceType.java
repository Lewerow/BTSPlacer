
package serialization.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for radioResourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="radioResourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="range" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "radioResourceType", propOrder = {
    "range"
})
public class JaxbRadioResourceType {

    protected double range;

    /**
     * Gets the value of the range property.
     * 
     */
    public double getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     */
    public void setRange(double value) {
        this.range = value;
    }

}
