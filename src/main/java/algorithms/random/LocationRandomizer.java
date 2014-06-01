package algorithms.random;

import calculations.PlacerLocation;

/**
 * Created by Ja on 29.03.14.
 */
public class LocationRandomizer {

	private final RandomGenerator randomGenerator;

	public LocationRandomizer(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public PlacerLocation randomLocation(double maxX, double maxY) {
		double x = randomGenerator.getDouble(maxX);
		double y = randomGenerator.getDouble(maxY);
		double baseX = PlacerLocation.getWroclawLocation().getX();
		double baseY = PlacerLocation.getWroclawLocation().getY();
		return PlacerLocation.getInstance(baseX + x, baseY + y);
	}
}
