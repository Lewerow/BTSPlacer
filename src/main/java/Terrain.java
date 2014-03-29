import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 29.03.14.
 */
public class Terrain {
    private List<BTS> btss = new LinkedList<BTS>();
    private double maxX;
    private double maxY;

    public Terrain(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addBTS(BTS bts) {
        assert bts.getLocation().x() <= maxX && bts.getLocation().y() <= maxY : "BTS must be inside terrain boundaries!";
        btss.add(bts);
    }

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

    public double signalLevel(Location l)
    {
        double signal = 0;
        for(BTS bts: btss) {
            signal += signalLevel(bts, l);
        }
        return signal;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }
}
