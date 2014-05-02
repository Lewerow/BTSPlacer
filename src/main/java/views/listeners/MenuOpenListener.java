package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.fest.util.VisibleForTesting;

import views.DrawingPanel;
import calculations.Terrain;
import calculations.TerrainGenerator;

public class MenuOpenListener implements ActionListener {

	private final JFileChooser fc;
	private final DrawingPanel drawingPanel;
	private final TerrainGenerator tg = TerrainGenerator.getInstance();

	// FIXME temporary solution - waiting for not random BTS Locations
	private Terrain terrain;

	public MenuOpenListener(JFileChooser fc, DrawingPanel drawingPanel) {
		this.fc = fc;
		this.drawingPanel = drawingPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fc.showOpenDialog(drawingPanel) == JFileChooser.APPROVE_OPTION) {
			File chosenFile = fc.getSelectedFile();

			try {
				BufferedImage image = ImageIO.read(chosenFile);
				terrain = generateDefaultTerrain(image.getWidth(), image.getHeight(), 30);
				drawingPanel.setMap(image);
				drawingPanel.resetTerrain(terrain);
			} catch (IOException ignore) {
			}
		}
	}

	private Terrain generateDefaultTerrain(int width, int height, int btsCount) {
		return tg.generateTerrainWithDefaultBTSs(width, height, btsCount);
	}

	@VisibleForTesting
	Terrain getTerrain() {
		return terrain;
	}

}
