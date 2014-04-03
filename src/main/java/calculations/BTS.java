package calculations;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 24.03.14.
 */
public class BTS {

	private Location location;
	private final List<BasebandResource> basebandResources = new LinkedList<BasebandResource>();
	private final List<RadioResource> radioResources = new LinkedList<RadioResource>();

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

	public double getMaxSignalLevel() {
		return getBBCapacity() * getRadioResourceCount();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location l) {
		location = l;
	}

	public List<RadioResource> getRadioResources() {
		return radioResources;
	}

	public List<BasebandResource> getBasebandResources() {
		return basebandResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BTS) {
			BTS other = (BTS) obj;
			return basebandResources.containsAll(other.getBasebandResources())
					&& radioResources.containsAll(other.getRadioResources())
					&& location.equals(other.getLocation());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("BTS(%s, %s ,%s)", location, basebandResources, radioResources);
	}

}
