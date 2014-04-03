package calculations;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGenerator {

	private static TerrainGenerator REFERENCE;
	private RandomGenerator randomGenerator;
	private int currentWidth;
	private int currentHeight;
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

	public Terrain generateTerrain(double maxX, double maxY, List<BTS> availableBTSs) {
		Terrain result = new Terrain(maxX, maxY);
		if (availableBTSs == null)
			return result;

		LocationRandomizer randomizer = new LocationRandomizer(randomGenerator);
		for (BTS bts : availableBTSs) {
			Location l = randomizer.randomLocation(maxX, maxY);
			assert l != null : "WTF? Randomizer must return SOME location!";

			bts.setLocation(l);
			result.addBTS(bts);
		}

		return result;
	}

	public BTS getDefaultBTS() {
		BTS bts = new BTS(Location.getInstance(0, 0));
		bts.addBBResource(new BasebandResource(10e6));
		bts.addRadioResource(new RadioResource());
		bts.addRadioResource(new RadioResource());

		return bts;
	}

	public Terrain generateTerrainWithDefaultBTSs(int maxX, int maxY, int btsCount) {
		currentHeight = maxY;
		currentWidth = maxX;
		this.btsCount = btsCount;

		assert btsCount >= 0 : "Cannot set negative number of BTSes!";

		LinkedList<BTS> btss = Lists.newLinkedList();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getDefaultBTS());

		return generateTerrain(maxX, maxY, btss);
	}

	public Terrain regenerateTerrain() {
		LinkedList<BTS> btss = new LinkedList<BTS>();
		for (int i = 0; i < btsCount; ++i)
			btss.add(getDefaultBTS());

		return generateTerrainWithDefaultBTSs(currentWidth, currentHeight, btsCount);
	}

	public void setBtsCount(Integer i) {
		btsCount = i;
	}

}
