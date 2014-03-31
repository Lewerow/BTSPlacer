/**
 * Created by Ja on 29.03.14.
 */
public class LocationRandomizer {

    private RandomGenerator rand;
    private Terrain terrain;

    public LocationRandomizer(Terrain t)
    {
        terrain = t;
        rand = new UniformRandomGenerator();
    }

    public LocationRandomizer(Terrain t, RandomGenerator r)
    {
        terrain = t;
        rand = r;
    }

    public Location randomLocation()
    {
        double x = rand.getDouble(terrain.getMaxX());
        double y = rand.getDouble(terrain.getMaxY());

        return new Location(x, y);
    }
}
