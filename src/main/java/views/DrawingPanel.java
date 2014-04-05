package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import views.btsrender.IBtsRenderer;
import calculations.BTS;
import calculations.Terrain;

/**
 * Created by user on 01.04.14.
 */
public class DrawingPanel extends JPanel implements TerrainDisplayer {

	private static final long serialVersionUID = -6523291909292131059L;

	private BufferedImage map;
	private Terrain terrain;
	private final IBtsRenderer btsRenderer;

	public DrawingPanel(IBtsRenderer btsRenderer) {
		this.btsRenderer = btsRenderer;
	}

	public void setMap(BufferedImage map) {
		this.map = map;
		repaint();
	}

	@Override
	public void resetTerrain(Terrain newTerrain) {
		this.terrain = newTerrain;
		btsRenderer.setTerrain(terrain);
		repaint();
	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		if (map != null) {
			g.drawImage(map, 0, 0, null);

			if (terrain != null) {
				drawTerrain(g);
			}
		}
	}

	private void drawTerrain(Graphics g) {
		List<BTS> btss = terrain.getBtss();
		for (BTS bts : btss) {
			btsRenderer.drawBts(g, bts);
		}

	}
}
