package algorithms.random;

import algorithms.Algorithm;
import calculations.*;
import com.google.common.collect.Lists;
import views.map.BTS;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGenerator implements Algorithm {

    public static final double maxXfromWroclaw = 0.18;
    public static final double maxYfromWroclaw = 0.22;
    private RandomGenerator randomGenerator;
    private int btsCount;
    private int subscriberCenterCount;

    public TerrainGenerator() {
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

    public BTS getRandomizedBTS() {
        UniformRandomGenerator generator = new UniformRandomGenerator();
        BTS bts = new BTS(PlacerLocation.getInstance(0, 0), BtsType.CIRCULAR);
        bts.addBBResource(new BasebandResource(generator.getDouble(300, 1000)));

        int radioCount = generator.getInt(1, 3);
        for (int i = 0; i < radioCount; ++i) {
            bts.addRadioResource(new RadioResource(generator.getDouble(0.1, 0.4)));
        }

        return bts;
    }

    public SubscriberCenter getRandomizedSC() {
        UniformRandomGenerator generator = new UniformRandomGenerator();
        SubscriberCenter sc = new SubscriberCenter(generator.getDouble(200, 4000),
                PlacerLocation.getInstance(0, 0),
                generator.getDouble(0.05, 0.3),
                generator.getDouble(0.05, 0.3));

        return sc;
    }

    public Terrain generateDefaultTerrainWithBTSsAndSubscribers(int btsCount, int subscriberCount) {
        this.btsCount = btsCount;

        assert btsCount >= 0 : "Cannot set negative number of BTSes!";

        LinkedList<BTS> btss = Lists.newLinkedList();
        for (int i = 0; i < btsCount; ++i)
            btss.add(getRandomizedBTS());

        LinkedList<SubscriberCenter> scs = Lists.newLinkedList();
        for (int i = 0; i < subscriberCount; ++i)
            scs.add(getRandomizedSC());

        return generateTerrain(btss, scs);
    }
    @Override
    public Terrain regenerateTerrain(Terrain currentTerrain) {
        return generateDefaultTerrainWithBTSsAndSubscribers(btsCount, subscriberCenterCount);
    }

    @Override
    public void setBtsCount(int btsCount) {
        this.btsCount = btsCount;
    }

    @Override
    public void setSubscriberCenterCount(int subscriberCenterCount) {
        this.subscriberCenterCount = subscriberCenterCount;
    }
}
