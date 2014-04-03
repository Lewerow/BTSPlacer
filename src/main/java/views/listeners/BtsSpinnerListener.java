package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.TerrainDisplayer;
import calculations.TerrainGenerator;

public class BtsSpinnerListener implements ChangeListener {

	private final TerrainGenerator tg = TerrainGenerator.getInstance();
	private final JSpinner btsCounter;
	private final TerrainDisplayer terrainDisplayer;

	public BtsSpinnerListener(JSpinner btsCounter, TerrainDisplayer terrainDisplayer) {
		this.btsCounter = btsCounter;
		this.terrainDisplayer = terrainDisplayer;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tg.setBtsCount((Integer) btsCounter.getValue());
		terrainDisplayer.resetTerrain(tg.regenerateTerrain());
	}
}
