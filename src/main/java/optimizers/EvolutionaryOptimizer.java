package optimizers;

import algorithms.Algorithm;
import algorithms.random.LocationRandomizer;
import algorithms.random.RandomGenerator;
import algorithms.random.TerrainGenerator;
import algorithms.random.UniformRandomGenerator;
import calculations.BasebandResource;
import calculations.PlacerLocation;
import calculations.RadioResource;
import calculations.Terrain;
import javafx.util.Pair;
import views.map.BTS;

import java.util.*;

/**
 * Created by Ja on 04.06.14.
 */
public class EvolutionaryOptimizer implements IBTSLocationOptimizer, Algorithm{
    private Vector<LinkedList<BTS> > populations = new Vector<LinkedList<BTS>>();
    int btsCount = 0;
    private Terrain terrain;

    PlacerLocation topLeft = PlacerLocation.getInstance(PlacerLocation.getWroclawLocation().getX(),
            PlacerLocation.getWroclawLocation().getY() + TerrainGenerator.maxYfromWroclaw);
    PlacerLocation bottomRight = PlacerLocation.getInstance(topLeft.getX() + TerrainGenerator.maxXfromWroclaw,
            topLeft.getY() - TerrainGenerator.maxYfromWroclaw);
    double step = TerrainGenerator.maxXfromWroclaw / 100;
    LocationRandomizer randomizer = new LocationRandomizer(new UniformRandomGenerator());

    @Override
    public Terrain regenerateTerrain(Terrain currentTerrain) {
        relocate(currentTerrain);
        return currentTerrain;
    }

    @Override
    public void setBtsCount(int btsCount) {
        this.btsCount = btsCount;
    }

    @Override
    public void setSubscriberCenterCount(int subscriberCenterCount) {

    }

    private class ItSucksToCreateClassesJustToCompare implements Comparable<ItSucksToCreateClassesJustToCompare>{

        public ItSucksToCreateClassesJustToCompare(int i, double g)
        {
            grade = g;
            id = i;
        }

        public double grade;
        public int id;

        @Override
        public int compareTo(ItSucksToCreateClassesJustToCompare o) {
            return (new Double(o.grade)).compareTo(new Double(grade));
        }
    }

    @Override
    public void relocate(Terrain t) {
        terrain = t;
        t.setBtss(new LinkedList<BTS>());

        double[][] requiredSignalLevel = t.getRequiredSignalLevelArray(topLeft, bottomRight, step);

        int populationSize = 15;
        initializePopulations(populationSize);

        int maxIterations = 10;
        LinkedList<BTS> currentBest = new LinkedList<BTS>();
        double currentBestGrade = -9999999999999999.8;

        for(int i = 0; i < maxIterations; ++i)
        {
            System.out.println(String.format("Performing iteration %d of evolutionary algorithm", i+1));
            Vector<ItSucksToCreateClassesJustToCompare> grades = new Vector<ItSucksToCreateClassesJustToCompare>();
            for(int j = 0; j < populationSize; ++j)
            {
                grades.add(new ItSucksToCreateClassesJustToCompare(j, grade(requiredSignalLevel, populations.get(j))));
            }

            Collections.sort(grades);
            if(grades.get(0).grade > currentBestGrade)
            {
                currentBest = populations.get(grades.get(0).id);
                currentBestGrade = grades.get(0).grade;
            }

            if(i == maxIterations - 1) break;

            Vector<Integer> chosen = new Vector<Integer>();
            int sumOfValues = (int) ((1.0 - Math.pow(0.95, populationSize)) * 20.0); //szereg geometryczny
            for(int j = 0; j < populationSize; ++j)
            {
                chosen.add(grades.get(firstSmaller(produceSequence(populationSize), new Random().nextInt(sumOfValues))).id);
            }

            mix(chosen);
            mutate();
        }

        terrain.setBtss(currentBest);
    }

    private void mutate() {
        for(int j = 0; j < populations.size(); ++j)
        {
            if(new Random().nextInt() % 100 < 10)
            {
                if(new Random().nextBoolean())
                {
                    List<BasebandResource> bb = populations.get(j).get((new Random().nextInt(populations.get(j).size()))).getBasebandResources();
                    bb.remove((new Random().nextInt(bb.size())));
                    bb.add(new BasebandResource(new UniformRandomGenerator().getDouble(300.0, 900.0)));
                }
                else
                {
                    List<RadioResource> rr = populations.get(j).get((new Random().nextInt(populations.get(j).size()))).getRadioResources();
                    rr.remove((new Random().nextInt(rr.size())));
                    rr.add(new RadioResource(new UniformRandomGenerator().getDouble(0.13, 0.27)));
                }
            }
        }
    }

    private void mix(Vector<Integer> chosen) {
        Vector<LinkedList<BTS> > oldPopulations = populations;
        populations = new Vector<LinkedList<BTS>>();

        for(int j = 0; j < oldPopulations.size(); ++j)
        {
            populations.add(cross(oldPopulations.get(chosen.get(j)), oldPopulations.get(chosen.get(new Random().nextInt(chosen.size())))));
        }
    }

    private LinkedList<BTS> cross(LinkedList<BTS> btses, LinkedList<BTS> btses1) {
        LinkedList<BTS> sum = new LinkedList<BTS>();

        for(int i = 0; i < btses.size(); ++i)
        {
            BTS newBTS = defaultBTS(btses.get(i).getLocation().middle(btses1.get(i).getLocation()));
            sum.add(newBTS);
        }

        return sum;
    }

    int firstSmaller(Vector<Integer> ints, int i)
    {
        for(int j = 0; j < ints.size(); ++j)
        {
            if(ints.get(j) > i)
                return j;
        }
        return 0;
    }

    Vector<Integer> produceSequence(int size)
    {
        Vector<Integer> ints = new Vector<Integer>();
        ints.add(10000);
        for(int i = 0; i < size; ++i)
        {
            ints.add((Integer) (int)(ints.lastElement().doubleValue() * 0.95));
        }
        return ints;
    }

    double grade(double[][] requiredSignalLevel, List<BTS> receivedBTSs)
    {
        terrain.setBtss(receivedBTSs);
        SignalDiffCalculator diffCalculator = new SignalDiffCalculator(terrain, topLeft, step);
        double[][] diff = diffCalculator.calculateDiff(diffCalculator.getSignalLevelArray(bottomRight),requiredSignalLevel);
        terrain.setBtss(new LinkedList<BTS>());

        double grade_ = 0;
        for(int i = 0; i < diff.length; ++i)
        {
            for(int j = 0; j < diff[i].length; ++j)
            {
                if(diff[i][j] > 0)
                    grade_ += diff[i][j];
                else
                    grade_ -= diff[i][j]/3;
            }
        }
        return grade_;
    }

    private void initializePopulations(int populationSize)
    {
        for(int i = 0; i < populationSize; ++i)
        {
            LinkedList<BTS> btses = new LinkedList<BTS>();
            for(int j = 0; j < btsCount; ++j)
            {
                btses.add(defaultBTS(randomizer.randomLocation(TerrainGenerator.maxXfromWroclaw, TerrainGenerator.maxYfromWroclaw)));
            }

            populations.add(btses);
        }
    }

    private BTS defaultBTS(PlacerLocation placerLocation) {
        BTS newOne = new BTS(placerLocation);

        newOne.addBBResource(new BasebandResource(600));
        newOne.addBBResource(new BasebandResource(600));
        newOne.addBBResource(new BasebandResource(600));
        newOne.addRadioResource(new RadioResource(0.2));
        newOne.addRadioResource(new RadioResource(0.2));

        return newOne;
    }


}
