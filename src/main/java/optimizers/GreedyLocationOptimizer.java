package optimizers;

import algorithms.random.TerrainGenerator;
import calculations.PlacerLocation;
import calculations.Terrain;
import de.fhpotsdam.unfolding.geo.Location;
import org.javatuples.Triplet;
import org.javatuples.Pair;
import views.map.BTS;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 01.06.14.
 */
public class GreedyLocationOptimizer implements IBTSLocationOptimizer {

    @Override
    public void relocate(Terrain t) {
        optimize(t, t.getBtss());
    }

    public double [][] getDiff(Terrain t, PlacerLocation topLeft, double step)
    {
        PlacerLocation bottomRight = PlacerLocation.getInstance(topLeft.getX() - TerrainGenerator.maxXfromWroclaw, topLeft.getY() - TerrainGenerator.maxYfromWroclaw);

        double[][] receivedSignalArray = t.getSignalLevelArray(topLeft, bottomRight);
        double[][] requiredSignalLevelArray = t.getRequiredSignalLevelArray(topLeft, bottomRight);
        double[][] diff = t.getRequiredSignalLevelArray(topLeft, bottomRight);

        assert receivedSignalArray.length == diff.length;
        for(int i = 0; i < diff.length; ++i)
        {
            assert receivedSignalArray[i].length == diff[i].length;
            for(int j = 0; j < diff[i].length; ++j)
                diff[i][j] = requiredSignalLevelArray[i][j] - receivedSignalArray[i][j];
        }

        return diff;
    }

    public void optimize(Terrain t, List<BTS> availableBTSs) {
        PlacerLocation topLeft = PlacerLocation.getWroclawLocation();
        double step = TerrainGenerator.maxXfromWroclaw / 400;
        while(!availableBTSs.isEmpty())
        {
            Triplet<Integer, Integer, Double> maxLocation = new Triplet<Integer, Integer, Double>(0, 0, 0.0);

            double [][] diff = getDiff(t, topLeft, step);
            for(int i = 0; i < diff.length; ++i)
            {
                for(int j = 0; j < diff[i].length; ++j)
                {
                    if(diff[i][j] > maxLocation.getValue2())
                    {
                        maxLocation = new Triplet<Integer, Integer, Double>(i, j, diff[i][j]);
                    }
                }
            }

            availableBTSs.get(0).setLocation(PlacerLocation.getInstance(topLeft.getX() - maxLocation.getValue0()*step,
                    topLeft.getY() - maxLocation.getValue1()*step));
            availableBTSs.remove(0);
        }
    }
}
