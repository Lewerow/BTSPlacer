import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 24.03.14.
 */
public class BTS {

    private Location location;
    private List<BasebandResource> basebandResources = new LinkedList<BasebandResource>();
    private List<RadioResource> radioResources = new LinkedList<RadioResource>();

    public BTS(Location location) {
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
        for (BasebandResource b : basebandResources)
            capacity += b.getCapacity();

        return capacity;
    }

    public double maxSignalLevel() {
        return getBBCapacity() * getRadioResourceCount();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location l) {
        location = l;
    }
}
