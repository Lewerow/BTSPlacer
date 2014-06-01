package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Algorithm;
import calculations.Terrain;
import views.TerrainDisplayer;
import algorithms.random.TerrainGenerator;

public class BtsSpinnerListener implements ChangeListener {

    private final Algorithm algorithm = new TerrainGenerator();
    private final JSpinner btsCounter;
    private final TerrainDisplayer terrainDisplayer;

    public BtsSpinnerListener(JSpinner btsCounter, TerrainDisplayer terrainDisplayer) {
        this.btsCounter = btsCounter;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Terrain currentTerrain = terrainDisplayer.getCurrentTerrain();
        algorithm.setBtsCount((Integer) btsCounter.getValue());
        algorithm.setSubscriberCenterCount(currentTerrain.getSubscriberCenters().size());
        terrainDisplayer.resetTerrain(algorithm.regenerateTerrain(currentTerrain));
    }
}
