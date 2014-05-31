
package serialization.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberCenter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberCenter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requiredSignal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sigmaX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sigmaY" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="locationX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="locationY" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriberCenter", propOrder = {
    "requiredSignal",
    "sigmaX",
    "sigmaY",
    "locationX",
    "locationY"
})
public class JaxbSubscriberCenter {

    protected double requiredSignal;
    protected double sigmaX;
    protected double sigmaY;
    protected double locationX;
    protected double locationY;

    /**
     * Gets the value of the requiredSignal property.
     * 
     */
    public double getRequiredSignal() {
        return requiredSignal;
    }

    /**
     * Sets the value of the requiredSignal property.
     * 
     */
    public void setRequiredSignal(double value) {
        this.requiredSignal = value;
    }

    /**
     * Gets the value of the sigmaX property.
     * 
     */
    public double getSigmaX() {
        return sigmaX;
    }

    /**
     * Sets the value of the sigmaX property.
     * 
     */
    public void setSigmaX(double value) {
        this.sigmaX = value;
    }

    /**
     * Gets the value of the sigmaY property.
     * 
     */
    public double getSigmaY() {
        return sigmaY;
    }

    /**
     * Sets the value of the sigmaY property.
     * 
     */
    public void setSigmaY(double value) {
        this.sigmaY = value;
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

}
