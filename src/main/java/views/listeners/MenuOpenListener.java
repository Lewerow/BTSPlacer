package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import views.MainWindowForm;
import calculations.Terrain;
import calculations.TerrainGenerator;
import calculations.UniformRandomGenerator;

public class MenuOpenListener implements ActionListener {

	private final JFileChooser fc;
	private final MainWindowForm mainWindow;

	// FIXME temporary solution - waiting for not random BTS Locations
	private final Terrain terrain = generateDefaultTerrain();

	public MenuOpenListener(JFileChooser fc, MainWindowForm mainWindowForm) {
		this.fc = fc;
		this.mainWindow = mainWindowForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fc.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
			File choosenFile = fc.getSelectedFile();

			try {
				BufferedImage image = ImageIO.read(choosenFile);
				mainWindow.setDrawingPanel(image, terrain);
			} catch (IOException ignore) {
			}
		}
	}

	private Terrain generateDefaultTerrain() {
		TerrainGenerator tg = new TerrainGenerator(new UniformRandomGenerator());
		return tg.generateTerrainWithDefaultBTSs(400, 400, 30);
	}

}
