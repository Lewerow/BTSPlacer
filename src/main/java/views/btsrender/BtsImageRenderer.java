package views.btsrender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import calculations.BTS;
import calculations.Location;
import calculations.Terrain;

public class BtsImageRenderer implements IBtsRenderer {

	private BufferedImage btsImage;

	public BtsImageRenderer() {
		try {
			// default BTS image
			btsImage = ImageIO.read(new File("src/main/resources/bts.png"));
		} catch (IOException e) {
			System.err.println("Could not load default bts image!");
			e.printStackTrace();
		}
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

		int width = 10;
		int height = 10;
		g.drawImage(btsImage, (int) x - width / 2, (int) y - height / 2, width, height, null);
	}
}
