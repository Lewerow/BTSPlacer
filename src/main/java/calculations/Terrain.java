package calculations;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 29.03.14.
 */
public class Terrain {
	private final List<BTS> btss = Lists.newLinkedList();

	public void addBTS(BTS bts) {
		assert bts.getLocation() != null : "BTS must have a location before placing on terrain!";
		btss.add(bts);
	}

	public List<BTS> getBtss() {
		return btss;
	}

	public double distance(PlacerLocation l1, PlacerLocation l2) {
		return l1.cartesianDistance(l2);
	}

	public double signalReduction(PlacerLocation l1, PlacerLocation l2) {
		// TODO implementation
		return 0;
	}

	public double distance(BTS bts, PlacerLocation l) {
		return distance(bts.getLocation(), l);
	}

	public double signalLevel(BTS bts, PlacerLocation l) {
		double distance = distance(bts, l);
		if (distance > bts.getRange())
			return 0;
		else if (distance == 0d)
			return bts.getMaxSignalLevel();
		else
			return bts.getMaxSignalLevel() * (1 - signalReduction(bts.getLocation(), l))
					/ Math.pow(distance, 2);
	}

	public double getSignalLevel(PlacerLocation l) {
		double signal = 0;
		for (BTS bts : btss) {
			signal += signalLevel(bts, l);
		}
		return signal;
	}

	public double getMaxAvailableSignalLevel() {
		double maxAvailableSignalLevel = 0d;
		for (BTS bts : btss) {
			double maxSignalLevel = bts.getMaxSignalLevel();
			if (maxSignalLevel > maxAvailableSignalLevel) {
				maxAvailableSignalLevel = maxSignalLevel;
			}
		}
		return maxAvailableSignalLevel;
	}
}
