package optimizers;

import algorithms.Algorithm;
import algorithms.random.TerrainGenerator;
import calculations.PlacerLocation;
import calculations.Terrain;
import org.javatuples.Triplet;
import views.map.BTS;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ja on 01.06.14.
 */
public class GreedyLocationOptimizer implements IBTSLocationOptimizer, Algorithm {

    LinkedList<BTS> btss = new LinkedList<BTS>();

    @Override
    public void relocate(Terrain t) {
        for(BTS b: t.getBtss())
            btss.add(b);

        optimize(t, btss);
    }

    public void optimize(Terrain t, List<BTS> availableBTSs) {
        System.out.println(String.format("Placing %d BTSs using greedy algorithm", availableBTSs.size()));
        PlacerLocation topLeft = PlacerLocation.getInstance(PlacerLocation.getWroclawLocation().getX(),
                PlacerLocation.getWroclawLocation().getY() + TerrainGenerator.maxYfromWroclaw);

        double step = TerrainGenerator.maxXfromWroclaw / 100;
        System.out.println(String.format("Using grid step: %.3f", step));
        while(!availableBTSs.isEmpty())
        {
            Triplet<Integer, Integer, Double> maxLocation = new Triplet<Integer, Integer, Double>(0, 0, 0.0);

            double [][] diff = new SignalDiffCalculator(t, topLeft, step).invoke();
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

            PlacerLocation newLocation = PlacerLocation.getInstance(topLeft.getX() + maxLocation.getValue0() * step,
                    topLeft.getY() - maxLocation.getValue1() * step);
            availableBTSs.get(0).setLocation(newLocation);
            availableBTSs.remove(0);
            System.out.println(String.format("Remaining BTSs: %d", availableBTSs.size()));
        }
    }

    @Override
    public Terrain regenerateTerrain(Terrain currentTerrain) {
        relocate(currentTerrain);
        return currentTerrain;
    }

    @Override
    public void setBtsCount(int btsCount) {

    }

    @Override
    public void setSubscriberCenterCount(int subscriberCenterCount) {

    }

}
