package serialization;

import calculations.PlacerLocation;
import calculations.SubscriberCenter;
import com.google.common.collect.Lists;
import serialization.jaxb.JaxbSubscriberCenter;
import serialization.jaxb.JaxbSubscriberList;
import serialization.jaxb.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class LoaderSaver {

    private LoaderSaver() {
    }

    public static List<SubscriberCenter> load(File loadFromFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        JaxbSubscriberList jaxbSubscribers = (JaxbSubscriberList) jaxbUnmarshaller.unmarshal(loadFromFile);

        List<JaxbSubscriberCenter> jaxbSubscribersList = jaxbSubscribers.getSubscriber();
        return getSubscriberCenters(jaxbSubscribersList);
    }

    private static List<SubscriberCenter> getSubscriberCenters(List<JaxbSubscriberCenter> jaxbSubscribersList) {
        List<SubscriberCenter> subscriberCenters = Lists.newArrayList();
        for (JaxbSubscriberCenter jaxbSubscriberCenter : jaxbSubscribersList) {
            SubscriberCenter subscriberCenter = getSubscriberCenter(jaxbSubscriberCenter);
            subscriberCenters.add(subscriberCenter);
        }
        return subscriberCenters;
    }

    private static SubscriberCenter getSubscriberCenter(JaxbSubscriberCenter jaxbSubscriberCenter) {
        double requiredSignal = jaxbSubscriberCenter.getRequiredSignal();
        PlacerLocation location = PlacerLocation.getInstance(jaxbSubscriberCenter.getLocationX(),
                jaxbSubscriberCenter.getLocationY());
        double sigmaX = jaxbSubscriberCenter.getSigmaX();
        double sigmaY = jaxbSubscriberCenter.getSigmaY();
        return new SubscriberCenter(requiredSignal, location, sigmaX, sigmaY);
    }

    public static void save(List<SubscriberCenter> subscriberCenters, File saveToFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        JaxbSubscriberList jaxbSubscribersList = createJaxbSubscriberCenters(subscriberCenters);
        jaxbMarshaller.marshal(jaxbSubscribersList, saveToFile);
    }

    private static JaxbSubscriberList createJaxbSubscriberCenters(List<SubscriberCenter> subscriberCenters) {
        ObjectFactory objectFactory = new ObjectFactory();
        JaxbSubscriberList jaxbSubscribers = objectFactory.createSubscriberList();
        List<JaxbSubscriberCenter> jaxbSubscriberList = jaxbSubscribers.getSubscriber();
        for (SubscriberCenter subscriber : subscriberCenters) {
            JaxbSubscriberCenter jaxbSubscriberCenter = createJaxbSubscriberCenter(objectFactory, subscriber);
            jaxbSubscriberList.add(jaxbSubscriberCenter);
        }
        return jaxbSubscribers;
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
