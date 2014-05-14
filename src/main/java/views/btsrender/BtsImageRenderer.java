package views.btsrender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import views.map.BTS;
import calculations.PlacerLocation;
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
		PlacerLocation location = bts.getLocation();
		double x = location.getX();
		double y = location.getY();

		int range = bts.getRange();

		g.drawImage(btsImage, (int) x - range, (int) y - range, range * 2, range * 2, null);
	}
}
