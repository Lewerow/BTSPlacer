package views.listeners;

import algorithms.Algorithm;
import calculations.Terrain;
import views.TerrainDisplayer;
import views.utils.AlgorithmSelectionHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vortim on 2014-06-01.
 */
public class GuiElemListener implements ActionListener, ChangeListener {

    private final JSpinner btsCounter;
    private JSpinner subscriberCenterCounter;
    private final TerrainDisplayer terrainDisplayer;

    public GuiElemListener(JSpinner btsCounter, JSpinner subscriberCenterCounter, TerrainDisplayer terrainDisplayer) {
        this.btsCounter = btsCounter;
        this.subscriberCenterCounter = subscriberCenterCounter;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        perform();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        perform();
    }

    private void perform() {
        Algorithm algorithm = AlgorithmSelectionHelper.getInstance().getSelectedAlgorithm();
        Terrain currentTerrain = terrainDisplayer.getCurrentTerrain();
        algorithm.setSubscriberCenterCount((Integer) subscriberCenterCounter.getValue());
        algorithm.setBtsCount((Integer) btsCounter.getValue());
        terrainDisplayer.resetTerrain(algorithm.regenerateTerrain(currentTerrain));
    }
}
