package views.map;

import com.google.common.collect.Lists;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MapApplet extends PApplet {
    private final Dimension size;
    private final MarkerManager<Marker> btsMarkerManager;
    private UnfoldingMap map;
    private ArrayList<AbstractMapTileUrlProvider> providers;
    private JPopupMenu popumMenu;

    public MapApplet(Dimension dimension) {
        super();
        this.size = dimension;
        btsMarkerManager = new MarkerManager<Marker>();
        init();
    }

    private JMenuItem createMicrosoftMapProviderItem(String name, final int i) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.mapDisplay.setProvider(providers.get(i));
                map.draw();
            }
        });
        return menuItem;
    }

    public void setup() {
        size(size.width, size.height);

        providers = Lists.newArrayList(new Google.GoogleMapProvider(), new Microsoft.AerialProvider(), new OpenStreetMap.OSMGrayProvider(), new OpenStreetMap.OpenStreetMapProvider());
        popumMenu = new JPopupMenu();
        popumMenu.add(createMicrosoftMapProviderItem("Google", 0));
        popumMenu.add(createMicrosoftMapProviderItem("OpenStreetMaps", 2));


        map = new UnfoldingMap(this, providers.get(0));

        // Show map around the location in the given zoom level.
        map.zoomAndPanTo(new Location(51.111, 17.030), 10);

        // Add mouse and keyboard interactions
        MapUtils.createDefaultEventDispatcher(this, map);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popumMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        map.addMarkerManager(btsMarkerManager);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println(map.getLocation(new ScreenPosition(e.getX(),e.getY())));
    }

    @Override
    public void draw() {
        map.draw();
    }

    public void addBtsMarker(Location location){
        btsMarkerManager.addMarker(new BtsMarker(location, BtsMarker.Type.DIRECTIONAL));
    }

    public void addBtsMarker(Location location, BtsMarker.Type type){
        btsMarkerManager.addMarker(new BtsMarker(location,type));
    }

}
