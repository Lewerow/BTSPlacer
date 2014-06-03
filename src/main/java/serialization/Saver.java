package serialization;

import calculations.BasebandResource;
import calculations.PlacerLocation;
import calculations.RadioResource;
import calculations.SubscriberCenter;
import serialization.jaxb.*;
import views.map.BTS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class Saver {

    private Saver() {
    }

    public static void save(List<SubscriberCenter> subscriberCenters, List<BTS> btss, File saveToFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        JaxbBtsPlacerElements jaxbBtsPlacerElements = createJaxbBtsPlacerElements(subscriberCenters, btss);
        jaxbMarshaller.marshal(jaxbBtsPlacerElements, saveToFile);
    }

    private static JaxbBtsPlacerElements createJaxbBtsPlacerElements(List<SubscriberCenter> subscriberCenters, List<BTS> btss) {
        ObjectFactory objectFactory = new ObjectFactory();
        JaxbBtsPlacerElements btsPlacerElements = objectFactory.createBtsPlacerElements();

        saveSubscriberCenters(subscriberCenters, objectFactory, btsPlacerElements);

        List<JaxbBtsType> jaxbBtss = btsPlacerElements.getBts();
        for (BTS bts : btss) {
            JaxbBtsType jaxbBtsType = objectFactory.createBtsType();
            jaxbBtsType.setCellType(bts.getBtsCellType().toString());
            PlacerLocation location = bts.getLocation();
            jaxbBtsType.setLocationX(location.getX());
            jaxbBtsType.setLocationY(location.getY());

            List<JaxbRadioResourceType> jaxbRadioResources = jaxbBtsType.getRadioResource();
            for (RadioResource radioResource : bts.getRadioResources()) {
                JaxbRadioResourceType jaxbRadioResourceType = objectFactory.createRadioResourceType();
                jaxbRadioResourceType.setRange(radioResource.getRange());
                jaxbRadioResources.add(jaxbRadioResourceType);
            }

            List<JaxbBasebandResourceType> jaxbBasebandResources = jaxbBtsType.getBasebandResource();
            for (BasebandResource bbResource : bts.getBasebandResources()) {
                JaxbBasebandResourceType jaxbBasebandResourceType = objectFactory.createBasebandResourceType();
                jaxbBasebandResourceType.setCapacity(bbResource.getCapacity());
                jaxbBasebandResources.add(jaxbBasebandResourceType);
            }

            jaxbBtss.add(jaxbBtsType);
        }

        return btsPlacerElements;
    }

    private static void saveSubscriberCenters(List<SubscriberCenter> subscriberCenters, ObjectFactory objectFactory, JaxbBtsPlacerElements btsPlacerElements) {
        List<JaxbSubscriberCenter> jaxbSubscriberList = btsPlacerElements.getSubscriber();
        for (SubscriberCenter subscriber : subscriberCenters) {
            JaxbSubscriberCenter jaxbSubscriberCenter = createJaxbSubscriberCenter(objectFactory, subscriber);
            jaxbSubscriberList.add(jaxbSubscriberCenter);
        }
    }

    private static JaxbSubscriberCenter createJaxbSubscriberCenter(ObjectFactory objectFactory, SubscriberCenter subscriber) {
        JaxbSubscriberCenter jaxbSubscriberCenter = objectFactory.createSubscriberCenter();
        jaxbSubscriberCenter.setRequiredSignal(subscriber.getRequiredSignal());
        jaxbSubscriberCenter.setLocationX(subscriber.getLocation().getX());
        jaxbSubscriberCenter.setLocationY(subscriber.getLocation().getY());
        jaxbSubscriberCenter.setSigmaX(subscriber.getVariance().getValue0());
        jaxbSubscriberCenter.setSigmaY(subscriber.getVariance().getValue1());
        return jaxbSubscriberCenter;
    }
}
