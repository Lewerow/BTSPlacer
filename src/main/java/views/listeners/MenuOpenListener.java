package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import serialization.DataContainer;
import serialization.Loader;
import serialization.Saver;
import views.TerrainDisplayer;
import calculations.SubscriberCenter;
import calculations.Terrain;
import views.map.BTS;

public class MenuOpenListener implements ActionListener {

    private JSpinner subscriberSpinner;
    private JSpinner btsSpinner;
    private TerrainDisplayer terrainDisplayer;

    public MenuOpenListener(JSpinner subscriberSpinner, JSpinner btsSpinner, TerrainDisplayer terrainDisplayer) {
        this.subscriberSpinner = subscriberSpinner;
        this.btsSpinner = btsSpinner;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String location = "";
        JFileChooser fc = new JFileChooser(location);
        fc.setFileFilter(new PlacerFileFilter());
        int dialogResponse = fc.showOpenDialog(subscriberSpinner);
        if (dialogResponse == JFileChooser.APPROVE_OPTION) {
            try {
                DataContainer loadedData = Loader.load(fc.getSelectedFile());

                Terrain terrain = new Terrain();

                setUpSubscriberCenters(loadedData, terrain);
                setUpBtss(loadedData, terrain);

                terrainDisplayer.resetTerrain(terrain);
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setUpBtss(DataContainer loadedData, Terrain terrain) {
        List<BTS> btss = loadedData.getBtss();
        terrain.setBtss(btss);
        btsSpinner.setValue(btss.size());
    }

    public void setUpSubscriberCenters(DataContainer loadedData, Terrain terrain) {
        List<SubscriberCenter> subscriberCenters = loadedData.getSubscriberCenters();
        terrain.setSubscriberCenters(subscriberCenters);
        subscriberSpinner.setValue(subscriberCenters.size());
    }
}
