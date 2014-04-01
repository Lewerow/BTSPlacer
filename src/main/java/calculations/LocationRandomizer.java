package calculations;

/**
 * Created by Ja on 29.03.14.
 */
public class LocationRandomizer {

	private final RandomGenerator rand;
	private final Terrain terrain;

	public LocationRandomizer(Terrain t) {
		terrain = t;
		rand = new UniformRandomGenerator();
	}

	public LocationRandomizer(Terrain t, RandomGenerator r) {
		terrain = t;
		rand = r;
	}

	public Location randomLocation() {
		double x = rand.getDouble(terrain.getMaxX());
		double y = rand.getDouble(terrain.getMaxY());

		return Location.getInstance(x, y);
	}
}
