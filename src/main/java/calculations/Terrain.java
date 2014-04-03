package calculations;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 29.03.14.
 */
public class Terrain {
	private final List<BTS> btss = Lists.newLinkedList();
	private final double maxX;
	private final double maxY;

	public Terrain(double maxX, double maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void addBTS(BTS bts) {
		assert bts.getLocation() != null : "BTS must have a location before placing on terrain!";
		assert bts.getLocation().getX() <= maxX && bts.getLocation().getY() <= maxY : "BTS must be inside terrain boundaries!";
		btss.add(bts);
	}

	public List<BTS> getBtss() {
		return btss;
	}

	public double distance(Location l1, Location l2) {
		return l1.cartesianDistance(l2);
	}

	public double signalReduction(Location l1, Location l2) {
		// TODO implementation
		return 0;
	}

	public double distance(BTS bts, Location l) {
		return distance(bts.getLocation(), l);
	}

	public double signalLevel(BTS bts, Location l) {
		double maxSignalLevel = bts.getMaxSignalLevel();
		double distance = distance(bts, l);
		if (distance == 0d)
			return maxSignalLevel;

		return maxSignalLevel * (1 - signalReduction(bts.getLocation(), l)) / Math.pow(distance, 2);
	}

	public double getSignalLevel(Location l) {
		double signal = 0;
		for (BTS bts : btss) {
			signal += signalLevel(bts, l);
		}
		return signal;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
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
