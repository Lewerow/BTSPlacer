import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 24.03.14.
 */
public class BTS {

    Location location;
    List<BasebandResource> basebandResources = new LinkedList<BasebandResource>();
    List<RadioResource> radioResources = new LinkedList<RadioResource>();

    public BTS(Location location)
    {
        this.location = location;
    }

    public void addBBResource(BasebandResource basebandResource) {
        basebandResources.add(basebandResource);
    }

    public int getBBResourceCount() {
        return basebandResources.size();
    }

    public int getRadioResourceCount() {
        return radioResources.size();
    }

    public void addRadioResource(RadioResource radioResource) {
        radioResources.add(radioResource);
    }

    public double getBBCapacity() {
        double capacity = 0;
        for(BasebandResource b : basebandResources)
            capacity += b.getCapacity();

        return capacity;
    }

    public double signalLevelAtLocation(Location l, Terrain t) {
        double maxSignalLevel = getBBCapacity() * getRadioResourceCount();
        double distance = t.distance(location, l);
        if(java.lang.Double.compare(distance, 0) == 0)
            return maxSignalLevel;

        return maxSignalLevel * (1 - t.signalReduction(location, l)) / Math.pow(distance, 2);
    }
}
