package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.TerrainDisplayer;
import calculations.TerrainGenerator;

/**
 * Created by Ja on 02.04.14.
 */
public class GenerateDistributionListener implements ActionListener {

    private final TerrainGenerator tg = TerrainGenerator.getInstance();
    private final TerrainDisplayer terrainDisplayer;

    public GenerateDistributionListener(TerrainDisplayer terrainDisplayer) {
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        terrainDisplayer.resetTerrain(tg.regenerateTerrain());
    }
}
