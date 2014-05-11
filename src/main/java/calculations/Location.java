package calculations;

import static java.lang.Math.*;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Created by Ja on 29.03.14.
 */
public class Location {

	private static final Location wroclawLocation = new Location(51.110141, 17.033153);
	private static final Map<String, Location> objects = Maps.newHashMap();
	private final double x;
	private final double y;

	public static Location getInstance(double x, double y) {
		String key = String.format("%f,%f", x, y);
		if (objects.containsKey(key))
			return objects.get(key);

		Location l = new Location(x, y);
		objects.put(key, l);
		return l;
	}

	public static Location getWroclawLocation() {
		return wroclawLocation;
	}

	private Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double cartesianDistance(Location l2) {
		return Math.sqrt(Math.pow(this.x - l2.x, 2) + Math.pow(this.y - l2.y, 2));
	}

	public List<Location> getLocationsAroundPoint(double maxAvailableX, double maxAvailableY) {
		List<Location> result = Lists.newArrayList();

		// left & same height
		result.add(Location.getInstance(max(0, x - 1d), y));

		// right & same height
		result.add(Location.getInstance(min(maxAvailableX, x + 1d), y));

		// same width & above
		result.add(Location.getInstance(x, max(0, y - 1d)));

		// same width & below
		result.add(Location.getInstance(x, min(maxAvailableY, y + 1d)));

		// up left corner
		result.add(Location.getInstance(max(0, x - 1d), max(0, y - 1d)));

		// up right corner
		result.add(Location.getInstance(min(maxAvailableX, x + 1d), max(0, y - 1d)));

		// down left corner
		result.add(Location.getInstance(max(0, x - 1d), min(maxAvailableY, y + 1d)));

		// down right corner
		result.add(Location.getInstance(min(maxAvailableX, x + 1d), min(maxAvailableY, y + 1d)));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location otherLocation = (Location) obj;
			return otherLocation.getX() == x && otherLocation.getY() == y;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Location( %f ; %f )", x, y);
	}

}
