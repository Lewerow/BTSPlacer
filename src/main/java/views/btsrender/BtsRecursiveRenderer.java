package views.btsrender;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import calculations.BTS;
import calculations.Location;
import calculations.Terrain;

import com.google.common.collect.Lists;

public class BtsRecursiveRenderer implements IBtsRenderer {

	private List<Location> alreadyPainted = Lists.newArrayList();
	private double maxAvailableSignalLevel;
	private Terrain terrain;

	@Override
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
		alreadyPainted = Lists.newArrayList();
		maxAvailableSignalLevel = terrain.getMaxAvailableSignalLevel();
	}

	@Override
	public void drawBts(Graphics g, BTS bts) {
		Location location = bts.getLocation();
		drawPixel(g, location);
	}

	private void drawPixel(Graphics g, Location location) {
		double signalLevel = terrain.getSignalLevel(location);

		boolean isStopCondition = alreadyPainted.contains(location)
				|| signalLevel < maxAvailableSignalLevel / 16;
		if (isStopCondition) {
			return;
		}

		int x = (int) location.getX();
		int y = (int) location.getY();

		double hue = signalLevel / maxAvailableSignalLevel;

		int transparency = Math.min(255, (int) (255 * hue));

		Color color = new Color(255, 0, 0, transparency);
		g.setColor(color);
		g.fillRect(x, y, 1, 1);

		alreadyPainted.add(location);

		List<Location> around = location.getLocationsAroundPoint(terrain.getMaxX(),
				terrain.getMaxY());
		for (Location loc : around) {
			drawPixel(g, loc);
		}

	}
}
