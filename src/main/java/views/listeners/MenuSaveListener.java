package views.listeners;

import calculations.SubscriberCenter;
import serialization.LoaderSaver;
import views.TerrainDisplayer;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

//TODO gui needs to be updated to handle saving
public class MenuSaveListener implements ActionListener {

    private TerrainDisplayer terrainDisplayer;
    private JPanel parentPanel;

    public MenuSaveListener(JPanel parentPanel, TerrainDisplayer terrainDisplayer) {
        this.parentPanel = parentPanel;
        this.terrainDisplayer = terrainDisplayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String location = "";
        JFileChooser fc = new JFileChooser(location);
        fc.setFileFilter(new PlacerFileFilter());
        int dialogResponse = fc.showSaveDialog(parentPanel);
        if (dialogResponse == JFileChooser.APPROVE_OPTION) {
            List<SubscriberCenter> subscriberCenters = terrainDisplayer.getCurrentTerrain().getSubscriberCenters();
            try {
                File selectedFile = fc.getSelectedFile();
                LoaderSaver.save(subscriberCenters, selectedFile);
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        }
    }
}
