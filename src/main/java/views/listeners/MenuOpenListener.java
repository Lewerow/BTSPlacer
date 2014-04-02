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
    private final TerrainGenerator tg;

	// FIXME temporary solution - waiting for not random BTS Locations
	private Terrain terrain;

	public MenuOpenListener(JFileChooser fc, TerrainGenerator tg, MainWindowForm mainWindowForm) {
		this.fc = fc;
        this.tg = tg;
		this.mainWindow = mainWindowForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fc.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
			File choosenFile = fc.getSelectedFile();

			try {
				BufferedImage image = ImageIO.read(choosenFile);
                terrain = generateDefaultTerrain(image.getWidth(), image.getHeight(), 30);
				mainWindow.setDrawingPanel(image, terrain);
			} catch (IOException ignore) {
			}
		}
	}

	private Terrain generateDefaultTerrain(int width, int height, int btsCount) {
		return tg.generateTerrainWithDefaultBTSs(width, height, btsCount);
	}

}
