package views.listeners;

import algorithms.Algorithm;
import algorithms.random.TerrainGenerator;
import calculations.PlacerLocation;
import calculations.Terrain;
import optimizers.SignalDiffCalculator;
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

        SignalDiffCalculator diff = new SignalDiffCalculator(currentTerrain, PlacerLocation.getInstance(PlacerLocation.getWroclawLocation().getX(),
                PlacerLocation.getWroclawLocation().getY() + TerrainGenerator.maxYfromWroclaw), TerrainGenerator.maxXfromWroclaw / 250);

        double[][] invoked = diff.invoke();
        double max = 0;
        double min = 0;
        double totalPlus = 0;
        double totalMinus = 0;

        for(double[]x  : invoked)
        {
            for(double y : x)
            {
                if(max < y)
                    max = y;
                if(min > y)
                    min = y;
                if(y > 0)
                    totalPlus += y;
                else
                    totalMinus += y;

            }
        }


        System.out.println("");
        System.out.println("======= Next Algorithm ========");
        System.out.println(String.format("Data for class: %s", algorithm.getClass().getName()));
        System.out.println(String.format("BTS count: %d, Subscriber Center count: %d",(Integer) btsCounter.getValue(),(Integer) subscriberCenterCounter.getValue()));
        System.out.println(String.format("Max lacking signal level: %f", max));
        System.out.println(String.format("Lacking signal: %f", totalPlus));
        System.out.println(String.format("Max too high signal: %f", -min));
        System.out.println(String.format("Total too high signal: %f", -totalMinus));

    }
}
