package calculations;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Ja on 29.03.14.
 */
public class LocationTest {
	@Test
	public void singleLocationIsOnPlaneXFirstYSecond() {
		Location l = Location.getInstance(10, 15);

		Assert.assertEquals(l.getX(), 10, 0.001);
		Assert.assertEquals(l.getY(), 15, 0.001);
	}

	@Test
	public void distanceBetweenSelfIsZero() {
		Location l = Location.getInstance(10, 10);
		Assert.assertEquals(l.cartesianDistance(l), 0, 0.0001);
	}

	@Test
	public void distanceBetweenSameXValueIsYValueDifference() {
		Location l1 = Location.getInstance(10, 20);
		Location l2 = Location.getInstance(10, 30);

		Assert.assertEquals(l1.cartesianDistance(l2), 10, 0.0001);
	}

	@Test
	public void distanceABIsEqualToDistanceBA() {
		Location l1 = Location.getInstance(10, 20);
		Location l2 = Location.getInstance(10, 30);

		Assert.assertEquals(l1.cartesianDistance(l2), l2.cartesianDistance(l1), 0.0001);
	}
}
