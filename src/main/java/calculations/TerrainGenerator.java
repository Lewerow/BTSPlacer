package calculations;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGenerator {

	private static final double maxXfromWroclaw = 0.05;
	private static final double maxYfromWroclaw = 0.05;
	private static TerrainGenerator REFERENCE;
	private RandomGenerator randomGenerator;
	private int btsCount;

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

	public Terrain generateTerrain(List<BTS> availableBTSs) {
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

		return result;
	}

	public BTS getDefaultBTS() {
		BTS bts = new BTS(PlacerLocation.getInstance(0, 0), BtsType.CIRCULAR);
		bts.addBBResource(new BasebandResource(100000));
		bts.addRadioResource(new RadioResource(50));
		bts.addRadioResource(new RadioResource(50));

		return bts;
	}

	public Terrain generateTerrainWithDefaultBTSs(int btsCount) {
		this.btsCount = btsCount;

		assert btsCount >= 0 : "Cannot set negative number of BTSes!";

		LinkedList<BTS> btss = Lists.newLinkedList();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getDefaultBTS());

		return generateTerrain(btss);
	}

	public Terrain regenerateTerrain() {
		List<BTS> btss = Lists.newArrayList();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getDefaultBTS());

		return generateTerrainWithDefaultBTSs(btsCount);
	}

	public void setBtsCount(Integer i) {
		btsCount = i;
	}

}
