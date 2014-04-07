package calculations;

import static org.mockito.Mockito.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Ja on 29.03.14.
 */
public class TerrainTest {

	private final Terrain terrain = new Terrain(40, 40);
	private final Location l1 = Location.getInstance(1, 20);
	private final Location l2 = Location.getInstance(11, 20);
	private final BTS bts = new BTS(l1);

	@Before
	public void setUp() {
	}

	@Test
	public void terrainCanCalculateDistanceBetweenLocations() {
		Assert.assertEquals(terrain.distance(l1, l2), 10, 0.00001);
	}

	@Test
	public void signalReductionRatioToSelfIs0() {
		Assert.assertEquals(terrain.signalReduction(l1, l1), 0, 0.0001);
	}

	@Test
	public void signalLevelDependsOnInvertSquareOfDistanceAndReductionRatio() {
		BasebandResource bb = mock(BasebandResource.class);
		Mockito.when(bb.getCapacity()).thenReturn(10.0);
		bts.addBBResource(bb);

		RadioResource rr1 = Mockito.mock(RadioResource.class);
		RadioResource rr2 = Mockito.mock(RadioResource.class);

		bts.addRadioResource(rr1);
		bts.addRadioResource(rr2);

		double signalLevel = bts.getMaxSignalLevel() * (1.0 / 100) * 1;
		Assert.assertEquals(terrain.signalLevel(bts, l2), signalLevel, 0.0001);
		Mockito.verify(bb, times(2)).getCapacity();
	}

	@Test
	public void totalSignalLevelIsSumOfSignalLevelsOfEachBTS() {
		BTS bts1 = new BTS(Location.getInstance(10, 10));
		bts1.addBBResource(new BasebandResource(100));
		bts1.addRadioResource(new RadioResource(10));

		BTS bts2 = new BTS(Location.getInstance(20, 10));
		bts2.addBBResource(new BasebandResource(599));
		bts2.addRadioResource(new RadioResource(10));
		bts2.addRadioResource(new RadioResource(10));

		terrain.addBTS(bts1);
		terrain.addBTS(bts2);

		double expectedSignalLevel = bts1.getMaxSignalLevel() * 1.0 / 100 + bts2.getMaxSignalLevel() * 1.0 / 400;
		Assert.assertEquals(expectedSignalLevel, terrain.getSignalLevel(Location.getInstance(0, 10)), 0.001);
	}

	@Test
	public void shouldFindMaxAvailableSignalLevel() {
		// given
		BTS bts1 = new BTS(Location.getInstance(20, 10));
		bts1.addBBResource(new BasebandResource(599));
		bts1.addRadioResource(new RadioResource(10));
		bts1.addRadioResource(new RadioResource(10));

		BTS bts2 = new BTS(Location.getInstance(40, 20));
		bts2.addBBResource(new BasebandResource(20));
		bts2.addRadioResource(new RadioResource(10));

		terrain.addBTS(bts1);
		terrain.addBTS(bts2);

		double maxSignalLevel = bts1.getMaxSignalLevel();

		// when
		double maxAvailableSignalLevel = terrain.getMaxAvailableSignalLevel();

		// then
		Assert.assertEquals(maxSignalLevel, maxAvailableSignalLevel);
	}
}
