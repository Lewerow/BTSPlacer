
package serialization.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the serialization.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: serialization.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JaxbBasebandResourceType }
     * 
     */
    public JaxbBasebandResourceType createBasebandResourceType() {
        return new JaxbBasebandResourceType();
    }

    /**
     * Create an instance of {@link JaxbBtsPlacerElements }
     * 
     */
    public JaxbBtsPlacerElements createBtsPlacerElements() {
        return new JaxbBtsPlacerElements();
    }

    /**
     * Create an instance of {@link JaxbBtsType }
     * 
     */
    public JaxbBtsType createBtsType() {
        return new JaxbBtsType();
    }

    /**
     * Create an instance of {@link JaxbRadioResourceType }
     * 
     */
    public JaxbRadioResourceType createRadioResourceType() {
        return new JaxbRadioResourceType();
    }

    /**
     * Create an instance of {@link JaxbSubscriberCenter }
     * 
     */
    public JaxbSubscriberCenter createSubscriberCenter() {
        return new JaxbSubscriberCenter();
    }

}
