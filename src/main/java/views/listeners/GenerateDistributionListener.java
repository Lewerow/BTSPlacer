package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.TerrainDisplayer;
import calculations.TerrainGenerator;

/**
 * Created by Ja on 02.04.14.
 */
public class GenerateDistributionListener implements ActionListener {

	private final TerrainGenerator tg = TerrainGenerator.getInstance();
	private final TerrainDisplayer displayer;

	public GenerateDistributionListener(TerrainDisplayer displayer) {
		this.displayer = displayer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		displayer.resetTerrain(tg.regenerateTerrain());
	}
}
