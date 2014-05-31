package views.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.TerrainDisplayer;
import calculations.TerrainGenerator;

/**
 * Created by Ja on 11.05.14.
 */

public class SubscriberCenterSpinnerListener implements ChangeListener {

    private final TerrainGenerator tg = TerrainGenerator.getInstance();
    private final JSpinner subscriberCenterCounter;
    private final TerrainDisplayer terrainDisplayer;

    public SubscriberCenterSpinnerListener(JSpinner subscriberCenterCounter,
            TerrainDisplayer terrainDisplayer) {
        this.subscriberCenterCounter = subscriberCenterCounter;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        tg.setSubscriberCenterCount((Integer) subscriberCenterCounter.getValue());
        terrainDisplayer.resetTerrain(tg.regenerateTerrain());
    }
}
