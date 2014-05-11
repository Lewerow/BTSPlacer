package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.map.MapApplet;
import calculations.TerrainGenerator;

/**
 * Created by Ja on 02.04.14.
 */
public class GenerateDistributionListener implements ActionListener {

	private final TerrainGenerator tg = TerrainGenerator.getInstance();
	private final MapApplet mapApplet;

	public GenerateDistributionListener(MapApplet mapApplet) {
		this.mapApplet = mapApplet;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mapApplet.resetTerrain(tg.regenerateTerrain());
	}
}
