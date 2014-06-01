package calculations;

import java.util.List;

import com.google.common.collect.Lists;
import de.fhpotsdam.unfolding.geo.Location;
import views.map.BTS;

/**
 * Created by Ja on 29.03.14.
 */
public class Terrain {
    private List<BTS> btss = Lists.newLinkedList();
    private List<SubscriberCenter> subscriberCenters = Lists.newLinkedList();

    public void addBTS(BTS bts) {
        assert bts.getLocation() != null : "BTS must have a location before placing on terrain!";
        btss.add(bts);
    }

    public void addSubscriberCenter(SubscriberCenter sc) {
        assert sc.getLocation() != null : "SubscriberCenter must have a location before placing on terrain!";
        subscriberCenters.add(sc);
    }

    public List<BTS> getBtss() {
        return btss;
    }

    public List<SubscriberCenter> getSubscriberCenters() {
        return subscriberCenters;
    }

    public void setBtss(List<BTS> btss) {
        this.btss = btss;
    }

    public void setSubscriberCenters(List<SubscriberCenter> subscriberCenters) {
        this.subscriberCenters = subscriberCenters;
    }

    public double distance(PlacerLocation l1, PlacerLocation l2) {
        return l1.cartesianDistance(l2);
    }

    public double signalReduction(PlacerLocation l1, PlacerLocation l2) {
        // TODO implementation
        return 0;
    }

    public double distance(BTS bts, PlacerLocation l) {
        return distance(bts.getLocation(), l);
    }

    public double signalLevel(BTS bts, PlacerLocation l) {
        double distance = distance(bts, l);
        if (distance > bts.getRange())
            return 0;
        else if (distance == 0d)
            return bts.getMaxSignalLevel();
        else
            return bts.getMaxSignalLevel() * (1 - signalReduction(bts.getLocation(), l))
                    / Math.pow(distance, 2);
    }

    public double getRequiredSignalLevel(PlacerLocation l) {
        double requested = 0;
        for (SubscriberCenter sc : subscriberCenters) {
            requested += sc.getRequiredSignalAt(l);
        }

        return requested;
    }

    public double getSignalLevel(PlacerLocation l) {
        double signal = 0;
        for (BTS bts : btss) {
            signal += signalLevel(bts, l);
        }
        return signal;
    }

    public double getMaxAvailableSignalLevel() {
        double maxAvailableSignalLevel = 0d;
        for (BTS bts : btss) {
            double maxSignalLevel = bts.getMaxSignalLevel();
            if (maxSignalLevel > maxAvailableSignalLevel) {
                maxAvailableSignalLevel = maxSignalLevel;
            }
        }
        return maxAvailableSignalLevel;
    }

    private interface ILevelGetter
    {
        public double getLevel(Location l);
    }

    private class SignalLevelGetter implements ILevelGetter
    {
        private Terrain terrain;
        public SignalLevelGetter(Terrain t)
        {
            terrain  = t;
        }
        public double getLevel(Location l)
        {
            return terrain.getSignalLevel((PlacerLocation)l);
        }
    }
    private class RequiredLevelGetter implements ILevelGetter
    {
        private Terrain terrain;
        public RequiredLevelGetter(Terrain t)
        {
            terrain  = t;
        }
        public double getLevel(Location l)
        {
            return terrain.getRequiredSignalLevel((PlacerLocation) l);
        }
    }
    public double[][] getSignalLevelArray(Location topLeft, Location bottomRight)
    {
        return getSignalLevelArray(topLeft, bottomRight, 0.0001);
    }

    public double[][] getSignalLevelArray(Location topLeft, Location bottomRight, double step)
    {
        return getLevelArray(topLeft, bottomRight, new SignalLevelGetter(this), 0.0001);
    }

    public double[][] getRequiredSignalLevelArray(Location topLeft, Location bottomRight)
    {
        return getRequiredSignalLevelArray(topLeft, bottomRight, 0.0001);
    }

    public double[][] getRequiredSignalLevelArray(Location topLeft, Location bottomRight, double step)
    {
        return getLevelArray(topLeft, bottomRight, new RequiredLevelGetter(this), 0.0001);
    }

    public double[][] getLevelArray(Location topLeft, Location bottomRight, ILevelGetter getter, double step)
    {
        assert step > 0;

        double width = topLeft.getLat() - bottomRight.getLat();
        assert width > 0;

        double height = topLeft.getLon() - bottomRight.getLon();
        assert height > 0;

        double[][] signalLevelArray = new double[(int)Math.ceil(width/step)][(int)Math.ceil(height/step)];

        for(int i = 0; topLeft.getLat() - i * step > bottomRight.getLat(); ++i)
        {
            for(int j = 0; topLeft.getLon() - j * step > bottomRight.getLon(); ++j)
            {
                signalLevelArray[i][j] = getter.getLevel(PlacerLocation.getInstance(topLeft.getLon() - j * step, topLeft.getLat() - i * step));
            }
        }

        return signalLevelArray;
    }
}
