package views.utils;

import algorithms.Algorithm;
import algorithms.algorithm2.Algorithm2;
import algorithms.algorithm3.Algorithm3;
import algorithms.random.TerrainGenerator;

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
            return new Algorithm2();
        }
        return new Algorithm3();
    }

}
