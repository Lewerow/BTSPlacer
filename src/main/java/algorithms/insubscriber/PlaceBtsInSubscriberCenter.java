package algorithms.insubscriber;

import algorithms.Algorithm;
import calculations.*;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import views.map.BTS;

import java.util.List;
import java.util.Stack;

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

        Stack<BTS> stack = sortBtss(currentTerrain.getBtss());

        for (SubscriberCenter sortedSubscriber : sortedSubscribers) {
            if (stack.isEmpty())
                break;

            BTS bts = stack.pop();
            relocateBts(sortedSubscriber, bts);
            terrain.addBTS(bts);
        }

        return terrain;
    }

    private Stack<BTS> sortBtss(List<BTS> btss) {
        List<BTS> sorted = Lists.newArrayList();
        for (BTS bts : btss) {
            int i = 0;
            for (BTS sortedBts : sorted) {
                if (sortedBts.getMaxSignalLevel() > bts.getMaxSignalLevel()) {
                    break;
                }
                i++;
            }
            sorted.add(i, bts);
        }
        Stack<BTS> stackedBtss = new Stack<BTS>();
        stackedBtss.addAll(sorted);
        return stackedBtss;
    }

    private void relocateBts(SubscriberCenter sortedSubscriber, BTS bts) {
        PlacerLocation subscriberLocation = sortedSubscriber.getLocation();
        PlacerLocation btsLocation = PlacerLocation.getInstance(subscriberLocation.getX(), subscriberLocation.getY());
        bts.setLocation(btsLocation);
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
