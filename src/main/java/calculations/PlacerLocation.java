package calculations;

import java.util.Map;

import algorithms.random.TerrainGenerator;
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

        double delta = 0.001;

        assert x == 0 || (x-delta) <= wroclawLocation.getX() + TerrainGenerator.maxXfromWroclaw : String.format("X: %.8f, expected < %.8f", x+delta, wroclawLocation.getX() + TerrainGenerator.maxXfromWroclaw);
        assert x == 0 || (x+delta) >= wroclawLocation.getX() : String.format("X: %.8f, expected > %.8f", x-delta, wroclawLocation.getX());
        assert y == 0 || (y-delta) <= wroclawLocation.getY() + TerrainGenerator.maxYfromWroclaw : String.format("Y: %.8f, expected < %.8f", y-delta, wroclawLocation.getY() + TerrainGenerator.maxYfromWroclaw);
        assert y == 0 || (y+delta) >= wroclawLocation.getY() : String.format("Y: %.8f, expected < %.8f", y+delta, wroclawLocation.getY());

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

    public PlacerLocation middle(PlacerLocation loc2)
    {
        return PlacerLocation.getInstance((x + loc2.x)/2, (y + loc2.y)/2);
    }

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double cartesianDistance(PlacerLocation l2) {
		return Math.sqrt(Math.pow(this.x - l2.x, 2) + Math.pow(this.y - l2.y, 2))*5; // magic factor
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
