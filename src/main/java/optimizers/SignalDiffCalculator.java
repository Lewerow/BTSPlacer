package optimizers;

import algorithms.random.TerrainGenerator;
import calculations.PlacerLocation;
import calculations.Terrain;

/**
* Created by Ja on 05.06.14.
*/
public class SignalDiffCalculator {
    private Terrain t;
    private PlacerLocation topLeft;
    private double step;

    public SignalDiffCalculator(Terrain t, PlacerLocation topLeft, double step) {
        this.t = t;
        this.topLeft = topLeft;
        this.step = step;
    }

    public double[][] invoke() {
        PlacerLocation bottomRight = PlacerLocation.getInstance(topLeft.getX() + TerrainGenerator.maxXfromWroclaw, topLeft.getY() - TerrainGenerator.maxYfromWroclaw);
        double[][] receivedSignalArray = getSignalLevelArray(bottomRight);
        double[][] requiredSignalLevelArray = getRequiredSignalLevelArray(bottomRight);

        return calculateDiff(receivedSignalArray, requiredSignalLevelArray);
    }

    public double[][] getSignalLevelArray(PlacerLocation bottomRight) {
        return t.getSignalLevelArray(topLeft, bottomRight, step);
    }

    public double[][] getRequiredSignalLevelArray(PlacerLocation bottomRight) {
        return t.getRequiredSignalLevelArray(topLeft, bottomRight, step);
    }

    public double[][] calculateDiff(double[][] receivedSignalArray, double[][] requiredSignalLevelArray) {
        double[][] diff = new double[requiredSignalLevelArray.length][requiredSignalLevelArray[0].length];

        assert receivedSignalArray.length == requiredSignalLevelArray.length;
        for(int i = 0; i < diff.length; ++i)
        {
            assert receivedSignalArray[i].length == requiredSignalLevelArray[i].length;
            for(int j = 0; j < diff[i].length; ++j)
            {
                diff[i][j] = requiredSignalLevelArray[i][j] - receivedSignalArray[i][j];
            }
        }

        return diff;
    }
}
