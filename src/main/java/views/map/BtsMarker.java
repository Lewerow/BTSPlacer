package views.map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.awt.*;
import java.util.Random;


public class BtsMarker extends AbstractMarker {

    private static final Color outOfOrder = Color.red;
    private static final Color degraded = Color.yellow;
    private static final Color activeColor = Color.blue;
    private static final int alpha = 20;

    private Type cellType;

    private float dist = 1.5f;

    public BtsMarker(Location location, Type cellType) {
        super(location);
        this.cellType = cellType;
        Random rand = new Random();
        dist = rand.nextFloat() % 2 + 1;
    }

    @Override
    public void draw(PGraphics p, float v, float v2, UnfoldingMap map) {
        p.noStroke();
        float distance = getDistance(getLocation(), dist, map);
        switch (cellType) {
            case DIRECTIONAL:
                drawOutOfOrderCells(p, v, v2, distance);
                break;
            case CIRCULAR:
                drawCircleCell(p, v, v2, distance);
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(PGraphics pGraphics, float v, float v2) {
    }

    private void drawOutOfOrderCells(PGraphics p, float x, float y, float distance) {
        p.fill(outOfOrder.getRGB(), alpha);
        drawGradient(p, distance, 0, 1.2f, x, y, 20);
        drawGradient(p, distance, 2, 1.2f, x, y, 20);
        drawGradient(p, distance, 4, 1.2f, x, y, 20);
    }

    private void drawCircleCell(PGraphics p, float x, float y, float distance) {
        p.fill(activeColor.getRGB(), alpha);
        drawGradient(p, distance, 0, 6.3f, x, y, 20);
    }

    private void drawGradient(PGraphics p, float distance, float startAngle, float scopeAngle, float x,
                              float y, int layerCount) {
        float distanceStep = distance / layerCount;
        float currentDistance = distanceStep;
        for (int i = layerCount; i > 0; i--) {
            p.arc(x, y, currentDistance, currentDistance, startAngle, startAngle + scopeAngle);
            currentDistance += distanceStep;
        }
    }

    private float getDistance(Location mainLocation, float size, UnfoldingMap map) {
        Location tempLocation = GeoUtils.getDestinationLocation(mainLocation, 90, size);
        ScreenPosition pos1 = map.getScreenPosition(mainLocation);
        ScreenPosition pos2 = map.getScreenPosition(tempLocation);
        return PApplet.dist(pos1.x, pos1.y, pos2.x, pos2.y);
    }

    @Override
    protected boolean isInside(float v, float v2, float v3, float v4) {
        return false;
    }

    public enum Type {
        DIRECTIONAL, CIRCULAR;
    }
}

