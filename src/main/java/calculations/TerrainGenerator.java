package calculations;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGenerator {

	private static final double maxXfromWroclaw = 0.18;
	private static final double maxYfromWroclaw = 0.22;
	private static TerrainGenerator REFERENCE;
	private RandomGenerator randomGenerator;
	private int btsCount;
    private int subscriberCenterCount;

    public static TerrainGenerator getInstance() {
		if (REFERENCE == null) {
			REFERENCE = new TerrainGenerator();
		}

		return REFERENCE;

	}

	private TerrainGenerator() {
		// default generator
		randomGenerator = new UniformRandomGenerator();
	}

	public void setRandomGenerator(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public Terrain generateTerrain(List<BTS> availableBTSs, List<SubscriberCenter> availableSCs) {
		Terrain result = new Terrain();
		if (availableBTSs == null)
			return result;

		LocationRandomizer randomizer = new LocationRandomizer(randomGenerator);
        for (BTS bts : availableBTSs) {
            PlacerLocation l = randomizer.randomLocation(maxXfromWroclaw, maxYfromWroclaw);
            assert l != null : "WTF? Randomizer must return SOME location!";

            bts.setLocation(l);
            result.addBTS(bts);
        }

        for (SubscriberCenter sc : availableSCs) {
            PlacerLocation l = randomizer.randomLocation(maxXfromWroclaw, maxYfromWroclaw);
            assert l != null : "WTF? Randomizer must return SOME location!";

            sc.setLocation(l);
            result.addSubscriberCenter(sc);
        }

        return result;
	}

    public BTS getDefaultBTS() {
        BTS bts = new BTS(PlacerLocation.getInstance(0, 0), BtsType.CIRCULAR);
        bts.addBBResource(new BasebandResource(1000));
        bts.addRadioResource(new RadioResource(50));
        bts.addRadioResource(new RadioResource(50));

        return bts;
    }

    public BTS getRandomizedBTS() {
        UniformRandomGenerator generator = new UniformRandomGenerator();
        BTS bts = new BTS(PlacerLocation.getInstance(0, 0), BtsType.CIRCULAR);
        bts.addBBResource(new BasebandResource(generator.getDouble(500, 2000)));

        int radioCount = generator.getInt(1, 3);
        for(int i = 0; i < radioCount; ++i)
        {
            bts.addRadioResource(new RadioResource(generator.getInt(30, 120)));
        }

        return bts;
    }

    public SubscriberCenter getDefaultSC() {
        SubscriberCenter sc = new SubscriberCenter(1000.0, PlacerLocation.getInstance(0, 0), 0.5, 0.5);
        return sc;
    }

    public SubscriberCenter getRandomizedSC() {
        UniformRandomGenerator generator = new UniformRandomGenerator();
        SubscriberCenter sc = new SubscriberCenter(generator.getDouble(200, 4000),
                PlacerLocation.getInstance(0, 0),
                generator.getDouble(0.2, 0.9),
                generator.getDouble(0.2, 0.9));

        return sc;
    }

    public Terrain generateDefaultTerrainWithBTSsAndSubscribers(int btsCount, int subscriberCount) {
		this.btsCount = btsCount;

		assert btsCount >= 0 : "Cannot set negative number of BTSes!";

		LinkedList<BTS> btss = Lists.newLinkedList();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getRandomizedBTS());

        LinkedList<SubscriberCenter> scs = Lists.newLinkedList();
        for(int i = 0; i < subscriberCount; ++i)
            scs.add(getRandomizedSC());
        
		return generateTerrain(btss, scs);
	}

	public Terrain regenerateTerrain() {
/*    TODO implement it as a different function - removing for now
		List<BTS> btss = Lists.newArrayList();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getDefaultBTS());
*/
		return generateDefaultTerrainWithBTSsAndSubscribers(btsCount, subscriberCenterCount);
	}

    public void setBtsCount(Integer i) {
        btsCount = i;
    }
    public void setSubscriberCenterCount(Integer i)
    { subscriberCenterCount = i; }

}
