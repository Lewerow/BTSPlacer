/**
 * Created by Ja on 29.03.14.
 */
public class Terrain {
    public double distance(Location l1, Location l2) {
        return l1.distance(l2);
    }

    public double signalReduction(Location l1, Location l2) {
        return 0;
    }

    public double distance(BTS bts, Location l)
    {
        return distance(bts.getLocation(), l);
    }

    public double signalLevel(BTS bts, Location l)
    {
        double maxSignalLevel = bts.maxSignalLevel();
        double distance = distance(bts, l);
        if(java.lang.Double.compare(distance, 0) == 0)
            return maxSignalLevel;

        return maxSignalLevel * (1 - signalReduction(bts.getLocation(), l)) / Math.pow(distance, 2);
    }
}
