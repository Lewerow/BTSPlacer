package calculations;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import org.javatuples.Pair;
import processing.core.PApplet;
import processing.core.PGraphics;
import views.map.MapApplet;

import java.awt.*;

/**
 * Created by Ja on 11.05.14. Assumption: SubscriberCenter represents center of
 * constant, circular distribution to be changed asap
 */
public class SubscriberCenter extends AbstractMarker {
    private double requiredSignal;
    private Pair<Double, Double> variance;

    private static final Color activeColor = Color.green;
    private final float diameterUnit = 0.5f;

    public SubscriberCenter(Double requiredSignal, Location center, Double sigmaX, Double sigmaY) {
        this.requiredSignal = requiredSignal;
        this.location = center;
        this.variance = new Pair<Double, Double>(sigmaX, sigmaY);
    }

    private float getDistance(Location mainLocation, float size, UnfoldingMap map) {
        Location tempLocation = GeoUtils.getDestinationLocation(mainLocation, 90, size);
        ScreenPosition pos1 = map.getScreenPosition(mainLocation);
        ScreenPosition pos2 = map.getScreenPosition(tempLocation);
        return PApplet.dist(pos1.x, pos1.y, pos2.x, pos2.y);
    }

    public double getRequiredSignalAt(PlacerLocation l) {
        if (getDistanceTo(l) < variance.getValue0() + variance.getValue1()) {
            return requiredSignal;
        }

        return 0;
    }

    @Override
    public PlacerLocation getLocation() {
        return (PlacerLocation) location;
    }

    @Override
    public void setLocation(Location l) {
        location = l;
    }

    @Override
    public void draw(PGraphics pGraphics, float v, float v2) {
    }

    private void drawGradient(PGraphics p, float distance, float startAngle, float scopeAngle,
                              float x, float y, int layerCount) {
        float distanceStep = distance / layerCount;
        float currentDistance = distanceStep;
        for (int i = layerCount; i > 0; i--) {
            p.arc(x, y, currentDistance, currentDistance, startAngle, startAngle + scopeAngle);
            currentDistance += distanceStep;
        }
    }

    private void drawCircularSubscribers(PGraphics p, float x, float y, float distance) {
        int transparency = (int) (0xff * (requiredSignal / MapApplet.maxRequestedSignalDrawn));
        p.fill(activeColor.getRGB(), transparency);
        drawGradient(p, distance, 0, 6.3f, x, y, 1);
    }

    @Override
    public void draw(PGraphics p, float v, float v2, UnfoldingMap map) {
        p.noStroke();
        float distance = getDistance(getLocation(),
                (float) (variance.getValue0() + variance.getValue1()) / diameterUnit, map);
        drawCircularSubscribers(p, v, v2, distance);
    }

    @Override
    protected boolean isInside(float v, float v2, float v3, float v4) {
        return false;
    }

    public Double getRequiredSignal() {
        return requiredSignal;
    }

    public Pair<Double, Double> getVariance() {
        return variance;
    }

    public void setVariance(Double sigmaX, Double sigmaY) {
        this.variance = new Pair<Double, Double>(sigmaX, sigmaY);
    }

    //TODO [DaKa] ToLe: better think if you are really want to return requiredSignal for SubscriberCenter,
    // if yes then this method is useless (you have getRequiredSignal() method above)
    public double getMaxRequiredSignal() {
        return requiredSignal;
    }
}
