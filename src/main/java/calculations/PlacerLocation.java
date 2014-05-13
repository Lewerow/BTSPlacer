package calculations;

import java.util.Map;

import com.google.common.collect.Maps;

import de.fhpotsdam.unfolding.geo.Location;

/**
 * Created by Ja on 29.03.14.
 */
public class PlacerLocation extends Location {

	private static final PlacerLocation wroclawLocation = new PlacerLocation(51.05, 16.9);
	private static final Map<String, PlacerLocation> objects = Maps.newHashMap();

	public static PlacerLocation getInstance(double x, double y) {
		String key = String.format("%f,%f", x, y);
		if (objects.containsKey(key))
			return objects.get(key);

		PlacerLocation l = new PlacerLocation(x, y);
		objects.put(key, l);
		return l;
	}

	public static PlacerLocation getWroclawLocation() {
		return wroclawLocation;
	}

	private PlacerLocation(double x, double y) {
		super(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double cartesianDistance(PlacerLocation l2) {
		return Math.sqrt(Math.pow(this.x - l2.x, 2) + Math.pow(this.y - l2.y, 2));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlacerLocation) {
			PlacerLocation otherLocation = (PlacerLocation) obj;
			return otherLocation.getX() == x && otherLocation.getY() == y;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Location( %f ; %f )", x, y);
	}

}
