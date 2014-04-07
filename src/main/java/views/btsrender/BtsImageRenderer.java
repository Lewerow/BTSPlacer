package views.btsrender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import calculations.BTS;
import calculations.Location;
import calculations.Terrain;

public class BtsImageRenderer implements IBtsRenderer {

	private final BufferedImage btsImage;

	public BtsImageRenderer() throws IOException {
		// default BTS image
		btsImage = ImageIO.read(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("bts.png"));
	}

	@Override
	public void setTerrain(Terrain terrain) {
	}

	@Override
	public void drawBts(Graphics g, BTS bts) {
		Location location = bts.getLocation();
		double x = location.getX();
		double y = location.getY();

		// FIXME scaling of image must depend on strength of provided signal !!!
		// Image img, int x, int y,
		// int width, int height,
		// ImageObserver observer

		int range = bts.getRange();

		g.drawImage(btsImage, (int) x - range / 2, (int) y - range / 2, range, range, null);
	}
}
