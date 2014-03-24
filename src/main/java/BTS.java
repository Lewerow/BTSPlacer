import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 24.03.14.
 */
public class BTS {

    List<BasebandResource> basebandResources;
    List<RadioResource> radioResources;

    public BTS()
    {
        basebandResources = new LinkedList<BasebandResource>();
        radioResources = new LinkedList<RadioResource>();
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
}
