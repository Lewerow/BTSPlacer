package views.utils;

import algorithms.Algorithm;
import algorithms.insubscriber.PlaceBtsInSubscriberCenter;
import algorithms.random.TerrainGenerator;
import optimizers.EvolutionaryOptimizer;
import optimizers.GreedyLocationOptimizer;
import optimizers.SingleBestFitLocationOptimizer;

import javax.swing.*;

/**
 * Created by Vortim on 2014-06-01.
 */
public class AlgorithmSelectionHelper {

    private static AlgorithmSelectionHelper REFERENCE;
    private JRadioButton[] radioButtons;

    public static AlgorithmSelectionHelper getInstance() {
        if (REFERENCE == null) {
            REFERENCE = new AlgorithmSelectionHelper();
        }
        return REFERENCE;
    }

    private AlgorithmSelectionHelper() {
    }

    public void initialize(JRadioButton... radioButtons) {
        this.radioButtons = radioButtons;
    }

    public Algorithm getSelectedAlgorithm() {
        if (radioButtons[0].isSelected()) {
            return new TerrainGenerator();
        }
        if (radioButtons[1].isSelected()) {
            return new PlaceBtsInSubscriberCenter();
        }
        if (radioButtons[2].isSelected()) {
            return new GreedyLocationOptimizer();
        }
        if(radioButtons[3].isSelected()){
            return new SingleBestFitLocationOptimizer();
        }

        return new EvolutionaryOptimizer();

    }
}
