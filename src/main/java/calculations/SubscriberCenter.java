package calculations;

import de.fhpotsdam.unfolding.marker.AbstractMarker;
import org.javatuples.Pair;
import processing.core.PGraphics;

import java.awt.*;

/**
 * Created by Ja on 11.05.14.
 * Assumption: SubscriberCenter represents center of 2D normal distribution
 */
public class SubscriberCenter extends AbstractMarker{
    PlacerLocation center;
    private Pair<Double, Double> variance;

    private static final Color activeColor = Color.green;
    private static final int alpha = 60;

    SubscriberCenter(PlacerLocation center, Double sigmaX, double sigmaY){
        this.center = center;
        this.variance = new Pair(sigmaX, sigmaY);
    }

    SubscriberCenter(PlacerLocation center, Pair<Double, Double> variance){
        this.center = center;
        this.variance = variance;
    }

    @Override
    public void draw(PGraphics pGraphics, float v, float v2) {
    }

    @Override
    protected boolean isInside(float v, float v2, float v3, float v4) {
        return false;
    }
}
