package calculations;

/**
 * Created by Ja on 29.03.14.
 */
public class LocationRandomizer {

	private final RandomGenerator randomGenerator;

	public LocationRandomizer() {
		randomGenerator = new UniformRandomGenerator();
	}

	public LocationRandomizer(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public Location randomLocation(double maxX, double maxY) {
		double x = randomGenerator.getDouble(maxX);
		double y = randomGenerator.getDouble(maxY);
		double baseX = Location.getWroclawLocation().getX();
		double baseY = Location.getWroclawLocation().getY();
		return Location.getInstance(baseX + x, baseY + y);
	}
}
