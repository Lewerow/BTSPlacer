package calculations;

import java.util.List;

import junit.framework.Assert;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

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

	@Test
	public void checkEqualityOfTwoInstances() {
		// given
		Location l1 = Location.getInstance(12d, 13d);
		Location l2 = Location.getInstance(12d, 13d);

		// when
		boolean isEqual = l1.equals(l2);

		// then
		Assert.assertEquals(true, isEqual);
	}

	@Test
	public void shouldFindMatchInList() {
		// given
		List<Location> locations = Lists.newArrayList();

		Location n1 = Location.getInstance(4.121212121212121212, 4.121212121212);
		Location n2 = Location.getInstance(4.12121212121212123, 4.121212121212);

		locations.add(n1);

		// when
		boolean contains = locations.contains(n2);

		// then
		Assertions.assertThat(contains).isTrue();
	}

	@Test
	public void shouldCreateLocationsAroundPoint() {
		// given
		Location base = Location.getInstance(5, 5);

		// when
		List<Location> locationsAroundPoint = base.getLocationsAroundPoint(10, 10);

		// then
		Location n1 = Location.getInstance(4, 4);
		Location n2 = Location.getInstance(4, 5);
		Location n3 = Location.getInstance(4, 6);

		Location n4 = Location.getInstance(5, 4);
		Location n5 = Location.getInstance(5, 6);

		Location n6 = Location.getInstance(6, 4);
		Location n7 = Location.getInstance(6, 5);
		Location n8 = Location.getInstance(6, 6);
		Assertions.assertThat(locationsAroundPoint).containsOnly(new Location[] { n1, n2, n3, n4, n5, n6, n7, n8 });

	}
}
