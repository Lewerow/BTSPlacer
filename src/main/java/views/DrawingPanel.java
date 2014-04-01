package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import calculations.Location;
import calculations.Terrain;

/**
 * Created by user on 01.04.14.
 */
public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = -6523291909292131059L;
	private BufferedImage image;
	private Terrain terrain;

	public DrawingPanel() {
		super();
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}

		if (terrain != null) {
			double maxAvailableSignalLevel = terrain.getMaxAvailableSignalLevel();

			for (int x = 0; x < terrain.getMaxX(); x++) {
				for (int y = 0; y < terrain.getMaxY(); y++) {

					double signalLevel = terrain.getSignalLevel(Location.getInstance(x, y));

					double hue = signalLevel / maxAvailableSignalLevel;

					int redColorDensity = Math.min(255, (int) (255 * hue));
					Color color = new Color(redColorDensity, 0, 0);
					g.setColor(color);
					g.fillRect(x, y, 1, 1);
				}
			}
		}
	}
}
