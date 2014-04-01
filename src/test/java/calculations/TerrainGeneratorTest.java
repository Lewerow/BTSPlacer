package calculations;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGeneratorTest {
	@Test
	public void terrainWithNoBTSsHasNoSignal() {
		RandomGenerator gen = Mockito.mock(RandomGenerator.class);
		TerrainGenerator tGen = new TerrainGenerator(gen);

		Terrain t = tGen.generateTerrain(10, 10, null);
		Assert.assertEquals(t.getSignalLevel(Location.getInstance(5, 5)), 0, 0.0001);
	}

	@Test
	public void terrainWithOneBTSsHasItsMaxSignalInOnePlace() {
		RandomGenerator gen = Mockito.mock(RandomGenerator.class);
		TerrainGenerator tGen = new TerrainGenerator(gen);

		BTS bts = new BTS(null);
		BTS spyBTS = Mockito.spy(bts);
		List<BTS> btsList = new LinkedList<BTS>();
		btsList.add(spyBTS);

		Mockito.when(spyBTS.getMaxSignalLevel()).thenReturn(100.0);
		Mockito.when(gen.getDouble(Mockito.any(Double.class))).thenReturn(5.0);

		Terrain t = tGen.generateTerrain(10, 10, btsList);
		Assert.assertEquals(t.getSignalLevel(Location.getInstance(5, 5)), 100.0, 0.0001);
	}
}
