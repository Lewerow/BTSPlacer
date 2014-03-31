import java.util.ArrayList;
import java.util.LinkedList;
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

    public BTS getDefaultBTS()
    {
        BTS bts = new BTS(null);
        bts.addBBResource(new BasebandResource(10000));
        bts.addRadioResource(new RadioResource());
        bts.addRadioResource(new RadioResource());

        return bts;
    }

    public Terrain generateTerrainWithDefaultBTSs(int maxX, int maxY, int btsCount)
    {
        LinkedList<BTS> btss = new LinkedList<BTS>();
        for(int i = 0; i < btsCount; ++i)
            btss.add(getDefaultBTS());

        return generateTerrain(maxX, maxY, btss);
    }
}
