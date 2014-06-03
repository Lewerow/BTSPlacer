package serialization;

import calculations.*;
import com.google.common.collect.Lists;
import serialization.jaxb.*;
import views.map.BTS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by Vortim on 2014-06-03.
 */
public class Loader {

    private Loader() {
    }

    public static DataContainer load(File loadFromFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        JaxbBtsPlacerElements btsPalcerElements = (JaxbBtsPlacerElements) jaxbUnmarshaller.unmarshal(loadFromFile);

        List<SubscriberCenter> subscriberCenters = getSubscriberCenters(btsPalcerElements);
        List<BTS> btss = getBtss(btsPalcerElements);

        return new DataContainer(btss, subscriberCenters);
    }

    public static List<BTS> getBtss(JaxbBtsPlacerElements btsPalcerElements) {
        List<BTS> btss = Lists.newArrayList();
        List<JaxbBtsType> jaxbBtss = btsPalcerElements.getBts();
        for (JaxbBtsType jaxbBts : jaxbBtss) {
            PlacerLocation location = PlacerLocation.getInstance(jaxbBts.getLocationX(), jaxbBts.getLocationY());
            BtsType btsType = BtsType.valueOf(jaxbBts.getCellType());
            BTS bts = new BTS(location, btsType);
            loadRadioResources(jaxbBts, bts);
            loadBasebandResources(jaxbBts, bts);

            btss.add(bts);
        }
        return btss;
    }

    public static void loadBasebandResources(JaxbBtsType jaxbBts, BTS bts) {
        List<JaxbBasebandResourceType> jaxbBasebandResources = jaxbBts.getBasebandResource();
        for(JaxbBasebandResourceType jaxbBasebandResource: jaxbBasebandResources){
            bts.addBBResource(new BasebandResource(jaxbBasebandResource.getCapacity()));
        }
    }

    public static void loadRadioResources(JaxbBtsType jaxbBts, BTS bts) {
        List<JaxbRadioResourceType> jaxbRadioResources = jaxbBts.getRadioResource();
        for(JaxbRadioResourceType jaxbRadioResource : jaxbRadioResources) {
            bts.addRadioResource(new RadioResource(jaxbRadioResource.getRange()));
        }
    }

    private static List<SubscriberCenter> getSubscriberCenters(JaxbBtsPlacerElements btsPalcerElements) {
        List<JaxbSubscriberCenter> jaxbSubscribersList = btsPalcerElements.getSubscriber();
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
}
