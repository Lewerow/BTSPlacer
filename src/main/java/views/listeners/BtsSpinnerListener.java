package views.listeners;

import calculations.TerrainGenerator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BtsSpinnerListener implements ChangeListener{

    private TerrainGenerator tg;
    private JSpinner btsCounter;

    public BtsSpinnerListener(TerrainGenerator tg, JSpinner btsCounter)
    {
        this.tg = tg;
        this.btsCounter = btsCounter;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        tg.setBtsCount((Integer)btsCounter.getValue());
    }

    // TODO implementation
}
