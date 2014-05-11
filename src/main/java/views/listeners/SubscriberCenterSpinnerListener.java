package views.listeners;

import calculations.TerrainGenerator;
import views.map.MapApplet;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Created by Ja on 11.05.14.
 */

public class SubscriberCenterSpinnerListener implements ChangeListener {

    private final TerrainGenerator tg = TerrainGenerator.getInstance();
    private final JSpinner subscriberCenterCounter;
    private final MapApplet mapApplet;

    public SubscriberCenterSpinnerListener(JSpinner subscriberCenterCounter, MapApplet mapApplet) {
        this.subscriberCenterCounter = subscriberCenterCounter;
        this.mapApplet = mapApplet;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        tg.setSubscriberCenterCount((Integer) subscriberCenterCounter.getValue());
        mapApplet.resetTerrain(tg.regenerateTerrain());
    }
}
