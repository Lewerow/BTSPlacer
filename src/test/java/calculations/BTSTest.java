package calculations;

import junit.framework.Assert;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import views.map.BTS;

/**
 * Created by Ja on 24.03.14.
 */
public class BTSTest {

	PlacerLocation btsLocation = PlacerLocation.getInstance(0, 0);
	PlacerLocation l = PlacerLocation.getInstance(0, 10);
	Terrain t = new Terrain();
	BTS bts = new BTS(btsLocation, BtsType.CIRCULAR);

	@Test
	public void atStartupBTSShallNotContainBBResources() {
		Assert.assertEquals(bts.getBBResourceCount(), 0);
	}

	@Test
	public void afterAddingSingleBBResourceBTSContainsOneBBResource() {
		bts.addBBResource(new BasebandResource(0));
		Assert.assertEquals(bts.getBBResourceCount(), 1);
	}

	@Test
	public void afterAddingTenBBResourcesBTSContainsTenBBResources() {
		for (int i = 0; i < 10; ++i)
			bts.addBBResource(new BasebandResource(0));

		Assert.assertEquals(bts.getBBResourceCount(), 10);
	}

	@Test
	public void atStartupBTSShallNotContainRadioResources() {
		Assert.assertEquals(bts.getRadioResourceCount(), 0);
	}

	@Test
	public void btsRangeIsMaxRadioRange() {
		Assert.assertEquals(bts.getRange(), 0);

		bts.addRadioResource(new RadioResource(10));
		Assert.assertEquals(bts.getRange(), 10);

		bts.addRadioResource(new RadioResource(100));
		Assert.assertEquals(bts.getRange(), 100);
	}

	@Test
	public void afterAddingSingleRadioResourceBTSContainsOneRadioResource() {
		bts.addRadioResource(new RadioResource(10));
		Assert.assertEquals(bts.getRadioResourceCount(), 1);
	}

	@Test
	public void afterAddingTenRadioResourceBTSContainsTenRadioResource() {
		for (int i = 0; i < 10; ++i)
			bts.addRadioResource(new RadioResource(10));

		Assert.assertEquals(bts.getRadioResourceCount(), 10);
	}

	@Test
	public void afterAddingTenRadioResourcesAndTenBBResourcesBTSContainsTenOfEach() {
		for (int i = 0; i < 10; ++i)
			bts.addRadioResource(new RadioResource(10));

		for (int i = 0; i < 10; ++i)
			bts.addBBResource(new BasebandResource(0));

		Assert.assertEquals(bts.getRadioResourceCount(), 10);
		Assert.assertEquals(bts.getBBResourceCount(), 10);
	}

	@Test
	public void BTSBasebandCapacityIsEqualToSumOfBBResourceCapacities() {
		bts.addBBResource(new BasebandResource(10));
		bts.addBBResource(new BasebandResource(30));
		bts.addBBResource(new BasebandResource(50));
		Assert.assertEquals(bts.getBBCapacity(), 90, 0.01);
	}

	@Test
	public void BTSWithoutRadiosEmitsNoSignal() {
		bts.addBBResource(new BasebandResource(50));
		Assert.assertEquals(bts.getMaxSignalLevel(), 0, 0.0001);
	}

	@Test
	public void BTSWithoutBBResourcesEmitsNoSignal() {
		bts.addRadioResource(new RadioResource(10));

		Assert.assertEquals(bts.getMaxSignalLevel(), 0, 0.0001);
	}

	@Test
	public void signalLevelAtBTSLocationIsEqualToItsBBCapacityTimenumberOfRadioModules() {
		bts.addBBResource(new BasebandResource(10));
		bts.addRadioResource(new RadioResource(10));
		bts.addRadioResource(new RadioResource(10));

		Assert.assertEquals(bts.getMaxSignalLevel(), 20, 0.0001);
	}

	@Test
	public void shouldBeEqual() {
		// given
		BTS b1 = new BTS(PlacerLocation.getInstance(5, 5), BtsType.CIRCULAR);
		b1.addBBResource(new BasebandResource(10d));
		b1.addBBResource(new BasebandResource(20d));
		b1.addRadioResource(new RadioResource(10));
		b1.addRadioResource(new RadioResource(10));

		BTS b2 = new BTS(PlacerLocation.getInstance(5, 5), BtsType.CIRCULAR);
		b2.addBBResource(new BasebandResource(10d));
		b2.addBBResource(new BasebandResource(20d));
		b2.addRadioResource(new RadioResource(10));
		b2.addRadioResource(new RadioResource(10));

		// then
		Assertions.assertThat(b1.equals(b2)).isTrue();
	}
}
