package views.listeners;

import calculations.TerrainGenerator;
import views.TerrainDisplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ja on 02.04.14.
 */
public class GenerateDistributionListener implements ActionListener{

    private TerrainGenerator tg;
    private TerrainDisplayer displayer;

    public GenerateDistributionListener(TerrainGenerator tg, TerrainDisplayer displayer)
    {
        this.tg = tg;
        this.displayer = displayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayer.resetTerrain(tg.regenerateTerrain());
    }
}
