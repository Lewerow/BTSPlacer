package optimizers;

import calculations.Terrain;
import views.map.BTS;

import java.util.LinkedList;

/**
 * Created by Ja on 01.06.14.
 */
public interface IBTSLocationOptimizer {
    public void relocate(Terrain t);
}
