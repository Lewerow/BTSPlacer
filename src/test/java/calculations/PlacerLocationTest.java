package calculations;

import java.util.List;

import junit.framework.Assert;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 29.03.14.
 */
public class PlacerLocationTest {
	@Test
	public void singleLocationIsOnPlaneXFirstYSecond() {
		PlacerLocation l = PlacerLocation.getInstance(10, 15);

		Assert.assertEquals(l.getX(), 10, 0.001);
		Assert.assertEquals(l.getY(), 15, 0.001);
	}

	@Test
	public void distanceBetweenSelfIsZero() {
		PlacerLocation l = PlacerLocation.getInstance(10, 10);
		Assert.assertEquals(l.cartesianDistance(l), 0, 0.0001);
	}

	@Test
	public void distanceBetweenSameXValueIsYValueDifference() {
		PlacerLocation l1 = PlacerLocation.getInstance(10, 20);
		PlacerLocation l2 = PlacerLocation.getInstance(10, 30);

		Assert.assertEquals(l1.cartesianDistance(l2), 10, 0.0001);
	}

	@Test
	public void distanceABIsEqualToDistanceBA() {
		PlacerLocation l1 = PlacerLocation.getInstance(10, 20);
		PlacerLocation l2 = PlacerLocation.getInstance(10, 30);

		Assert.assertEquals(l1.cartesianDistance(l2), l2.cartesianDistance(l1), 0.0001);
	}

	@Test
	public void checkEqualityOfTwoInstances() {
		// given
		PlacerLocation l1 = PlacerLocation.getInstance(12d, 13d);
		PlacerLocation l2 = PlacerLocation.getInstance(12d, 13d);

		// when
		boolean isEqual = l1.equals(l2);

		// then
		Assert.assertEquals(true, isEqual);
	}

	@Test
	public void shouldFindMatchInList() {
		// given
		List<PlacerLocation> locations = Lists.newArrayList();

		PlacerLocation n1 = PlacerLocation.getInstance(4.121212121212121212, 4.121212121212);
		PlacerLocation n2 = PlacerLocation.getInstance(4.12121212121212123, 4.121212121212);

		locations.add(n1);

		// when
		boolean contains = locations.contains(n2);

		// then
		Assertions.assertThat(contains).isTrue();
	}

	@Test
	public void shouldCheckFlyweightPattern() {
		// given
		PlacerLocation l1 = PlacerLocation.getInstance(5d, 5d);
		PlacerLocation l2 = PlacerLocation.getInstance(5d, 5d);

		// when
		int systemHashCodeL1 = System.identityHashCode(l1);
		int systemHashCodeL2 = System.identityHashCode(l2);

		// then
		Assertions.assertThat(systemHashCodeL1).isEqualTo(systemHashCodeL2);
	}
}
