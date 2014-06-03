
package serialization.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for btsPlacerElements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="btsPlacerElements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriber" type="{}subscriberCenter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="bts" type="{}btsType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "btsPlacerElements", propOrder = {
    "subscriber",
    "bts"
})
@XmlRootElement
public class JaxbBtsPlacerElements {

    protected List<JaxbSubscriberCenter> subscriber;
    protected List<JaxbBtsType> bts;

    /**
     * Gets the value of the subscriber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JaxbSubscriberCenter }
     * 
     * 
     */
    public List<JaxbSubscriberCenter> getSubscriber() {
        if (subscriber == null) {
            subscriber = new ArrayList<JaxbSubscriberCenter>();
        }
        return this.subscriber;
    }

    /**
     * Gets the value of the bts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JaxbBtsType }
     * 
     * 
     */
    public List<JaxbBtsType> getBts() {
        if (bts == null) {
            bts = new ArrayList<JaxbBtsType>();
        }
        return this.bts;
    }

}
