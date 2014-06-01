package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Algorithm;
import calculations.Terrain;
import views.TerrainDisplayer;
import algorithms.random.TerrainGenerator;

/**
 * Created by Ja on 11.05.14.
 */

public class SubscriberCenterSpinnerListener implements ChangeListener {

    private final Algorithm algorithm = new TerrainGenerator();
    private final JSpinner subscriberCenterCounter;
    private final TerrainDisplayer terrainDisplayer;

    public SubscriberCenterSpinnerListener(JSpinner subscriberCenterCounter,
            TerrainDisplayer terrainDisplayer) {
        this.subscriberCenterCounter = subscriberCenterCounter;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Terrain currentTerrain = terrainDisplayer.getCurrentTerrain();
        algorithm.setSubscriberCenterCount((Integer) subscriberCenterCounter.getValue());
        algorithm.setBtsCount(currentTerrain.getBtss().size());
        terrainDisplayer.resetTerrain(algorithm.regenerateTerrain(currentTerrain));
    }
}
