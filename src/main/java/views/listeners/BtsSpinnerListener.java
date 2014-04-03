package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import calculations.TerrainGenerator;

public class BtsSpinnerListener implements ChangeListener {

	private final TerrainGenerator tg = TerrainGenerator.getInstance();
	private final JSpinner btsCounter;

	public BtsSpinnerListener(JSpinner btsCounter) {
		this.btsCounter = btsCounter;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tg.setBtsCount((Integer) btsCounter.getValue());
	}

	// TODO implementation
}
