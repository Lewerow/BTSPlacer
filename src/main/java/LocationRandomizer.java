/**
 * Created by Ja on 29.03.14.
 */
public class LocationRandomizer {
    Location randomLocation(RandomGenerator rand, Terrain t)
    {
        double x = rand.getDouble(t.getMaxX());
        double y = rand.getDouble(t.getMaxY());

        return new Location(x, y);
    }
}
