package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithms.Algorithm;
import views.TerrainDisplayer;
import algorithms.random.TerrainGenerator;

/**
 * Created by Ja on 02.04.14.
 */
public class GenerateDistributionListener implements ActionListener {

    private final Algorithm algorithm = new TerrainGenerator();
    private final TerrainDisplayer terrainDisplayer;

    public GenerateDistributionListener(TerrainDisplayer terrainDisplayer) {
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        terrainDisplayer.resetTerrain(algorithm.regenerateTerrain(terrainDisplayer.getCurrentTerrain()));
    }
}
