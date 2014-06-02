package algorithms.insubscriber;

import algorithms.Algorithm;
import calculations.*;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import de.fhpotsdam.unfolding.geo.Location;
import views.map.BTS;

import java.util.List;

/**
 * Created by Vortim on 2014-06-01.
 */
public class PlaceBtsInSubscriberCenter implements Algorithm {
    private final int DEFAULT_BB_CAPACITY = 500;
    private int btsCount;
    private int subscriberCenterCount;

    @Override
    public Terrain regenerateTerrain(Terrain currentTerrain) {
        Terrain terrain = new Terrain();
        List<SubscriberCenter> subscriberCenters = currentTerrain.getSubscriberCenters();
        terrain.setSubscriberCenters(subscriberCenters);

        List<SubscriberCenter> sortedSubscribers = sortSubscriberCenter(subscriberCenters);

        for (SubscriberCenter sortedSubscriber : sortedSubscribers) {
            if (btsCount == 0)
                break;

            terrain.addBTS(createProperBts(sortedSubscriber));
            if (terrain.getBtss().size() >= btsCount)
                break;
        }

        return terrain;
    }

    private BTS createProperBts(SubscriberCenter sortedSubscriber) {
        PlacerLocation subscriberLocation = sortedSubscriber.getLocation();
        PlacerLocation btsLocation = PlacerLocation.getInstance(subscriberLocation.getX(), subscriberLocation.getY());
        BTS bts = new BTS(btsLocation, BtsType.CIRCULAR);
        bts.addBBResource(new BasebandResource(DEFAULT_BB_CAPACITY));
        bts.addBBResource(new BasebandResource(DEFAULT_BB_CAPACITY));
        bts.addBBResource(new BasebandResource(DEFAULT_BB_CAPACITY));
        //FIXME radioResource should be proper to subscriber center
        bts.addRadioResource(new RadioResource(0.5));

        return bts;
    }

    @VisibleForTesting
    List<SubscriberCenter> sortSubscriberCenter(List<SubscriberCenter> subscriberCenters) {
        List<SubscriberCenter> sorted = Lists.newArrayList();
        for (SubscriberCenter subscriberCenter : subscriberCenters) {
            int i = 0;
            for (SubscriberCenter sortedSubscriber : sorted) {
                if (sortedSubscriber.getRequiredSignal() < subscriberCenter.getRequiredSignal()) {
                    break;
                }
                i++;
            }
            sorted.add(i, subscriberCenter);
        }
        return sorted;
    }

    @Override
    public void setBtsCount(int btsCount) {
        this.btsCount = btsCount;
    }

    @Override
    public void setSubscriberCenterCount(int subscriberCenterCount) {
        this.subscriberCenterCount = subscriberCenterCount;
    }
}
