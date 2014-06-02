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
        return 0;
    }

    public double signalLevel(BTS bts, PlacerLocation l) {
        double distance = bts.getLocation().cartesianDistance(l);
        if (distance > bts.getRange())
            return 0;
        else
            return bts.getMaxSignalLevel() * (1 - signalReduction(bts.getLocation(), l))
                    / (Math.pow(distance, 2) + 1);
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
        return getSignalLevelArray(topLeft, bottomRight, 0.001);
    }

    public double[][] getSignalLevelArray(Location topLeft, Location bottomRight, double step)
    {
        return getLevelArray(topLeft, bottomRight, new SignalLevelGetter(this), step);
    }

    public double[][] getRequiredSignalLevelArray(Location topLeft, Location bottomRight)
    {
        return getRequiredSignalLevelArray(topLeft, bottomRight, 0.001);
    }

    public double[][] getRequiredSignalLevelArray(Location topLeft, Location bottomRight, double step)
    {
        return getLevelArray(topLeft, bottomRight, new RequiredLevelGetter(this), step);
    }

    public double[][] getLevelArray(Location topLeft, Location bottomRight, ILevelGetter getter, double step)
    {
        assert step > 0;

        double width = bottomRight.getLat() - topLeft.getLat();
        assert width > 0 : "Grid width < 0";

        double height = topLeft.getLon() - bottomRight.getLon();
        assert height > 0 : "Grid height < 0";

        int rows = (int)Math.ceil(width/step);
        int cols = (int)Math.ceil(height/step);
        double[][] levelArray = new double[rows][cols];

        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j < cols; ++j)
            {
/*                assert x == 0 || x <= wroclawLocation.getX() + TerrainGenerator.maxXfromWroclaw;
                assert x == 0 || x >= wroclawLocation.getX();
                assert y == 0 || y <= wroclawLocation.getY() + TerrainGenerator.maxYfromWroclaw;
                assert y == 0 || y >= wroclawLocation.getY();*/
                levelArray[i][j] = getter.getLevel(PlacerLocation.getInstance(topLeft.getLat() + i * step, topLeft.getLon() - j * step));
            }
        }

        return levelArray;
    }
}
