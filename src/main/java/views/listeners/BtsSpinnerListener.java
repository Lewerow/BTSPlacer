package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.map.MapApplet;
import calculations.TerrainGenerator;

public class BtsSpinnerListener implements ChangeListener {

	private final TerrainGenerator tg = TerrainGenerator.getInstance();
	private final JSpinner btsCounter;
	private final MapApplet mapApplet;

	public BtsSpinnerListener(JSpinner btsCounter, MapApplet mapApplet) {
		this.btsCounter = btsCounter;
		this.mapApplet = mapApplet;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tg.setBtsCount((Integer) btsCounter.getValue());
		mapApplet.resetTerrain(tg.regenerateTerrain());
	}
}
