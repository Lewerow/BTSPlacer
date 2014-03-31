import java.util.List;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGenerator {

    RandomGenerator randomGenerator;

    public TerrainGenerator(RandomGenerator generator) {
        randomGenerator = generator;
    }

    public Terrain generateTerrain(int maxX, int maxY, List<BTS> availableBTSs)
    {
        Terrain t = new Terrain(maxX, maxY);
        if(availableBTSs == null)
            return t;

        LocationRandomizer randomizer = new LocationRandomizer(t, randomGenerator);
        for(BTS bts: availableBTSs)
        {
            Location l = randomizer.randomLocation();
            assert l != null : "WTF? Randomizer must return SOME location!";

            bts.setLocation(l);
            t.addBTS(bts);
        }

        return t;
    }
}
