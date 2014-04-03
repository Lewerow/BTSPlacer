package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import calculations.BTS;
import calculations.Location;
import calculations.Terrain;

import com.google.common.collect.Lists;

/**
 * Created by user on 01.04.14.
 */
public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = -6523291909292131059L;

	private List<Location> alreadyPainted = Lists.newArrayList();

	private BufferedImage image;
	private Terrain terrain;
	private double maxAvailableSignalLevel;

	public DrawingPanel() {
		super();
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		validate();
		repaint();
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
		alreadyPainted = Lists.newArrayList();
		maxAvailableSignalLevel = terrain.getMaxAvailableSignalLevel();
		validate();
		repaint();
	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);

			if (terrain != null) {
				drawTerrain(g);
			}
		}
	}

	private void drawTerrain(Graphics g) {
		List<BTS> btss = terrain.getBtss();
		for (BTS bts : btss) {
			drawBts(g, bts);
		}

	}

	private void drawBts(Graphics g, BTS bts) {
		Location location = bts.getLocation();
		drawPixel(g, location);

	}

	private void drawPixel(Graphics g, Location location) {
		double signalLevel = terrain.getSignalLevel(location);
		if (alreadyPainted.contains(location) || signalLevel < maxAvailableSignalLevel / 32) {
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

		List<Location> around = location.createLocationsAroundPoint(terrain.getMaxX(), terrain.getMaxY());
		for (Location loc : around) {
			drawPixel(g, loc);
		}

	}
}
