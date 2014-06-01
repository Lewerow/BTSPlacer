package views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import serialization.LoaderSaver;
import views.TerrainDisplayer;
import calculations.SubscriberCenter;
import calculations.Terrain;

public class MenuOpenListener implements ActionListener {

    private JSpinner subscriberSpinner;
    private TerrainDisplayer terrainDisplayer;

    public MenuOpenListener(JSpinner subscriberSpinner, TerrainDisplayer terrainDisplayer) {
        this.subscriberSpinner = subscriberSpinner;
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
                List<SubscriberCenter> subscribers = LoaderSaver.load(fc.getSelectedFile());

                Terrain terrain = new Terrain();
                for (SubscriberCenter subscriberCenter : subscribers) {
                    terrain.addSubscriberCenter(subscriberCenter);

                }
                subscriberSpinner.setValue(subscribers.size());
                terrainDisplayer.resetTerrain(terrain);
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        }
    }
}
