import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ja on 29.03.14.
 */
public class TerrainTest {

    private Terrain t = new Terrain();
    private Location l1 = new Location(1, 20);
    private Location l2 = new Location(11, 20);
    private BTS bts = new BTS(l1);

    @Before
    public void setUp()
    {
    }

    @Test
    public void terrainCanCalculateDistanceBetweenLocations()
    {
        Assert.assertEquals(t.distance(l1, l2), 10, 0.00001);
    }

    @Test
    public void signalReductionRatioToSelfIs0()
    {
        Assert.assertEquals(t.signalReduction(l1, l1), 0, 0.0001);
    }

    @Test
    public void signalLevelDependsOnInvertSquareOfDistanceAndReductionRatio()
    {
        bts.addBBResource(new BasebandResource(10));
        bts.addRadioResource(new RadioResource());
        bts.addRadioResource(new RadioResource());

        double signalLevel = bts.maxSignalLevel() * (1.0/100) * 1;
        Assert.assertEquals(t.signalLevel(bts, l2), signalLevel, 0.0001);
    }

    @Test
    public void totalSignalLevelIsSumOfSignalLevelsOfEachBTS()
    {
        BTS bts1 = new BTS(new Location(10, 10));
        bts1.addBBResource(new BasebandResource(100));
        bts1.addRadioResource(new RadioResource());

        BTS bts2 = new BTS(new Location(20, 10));
        bts2.addBBResource(new BasebandResource(599));
        bts2.addRadioResource(new RadioResource());
        bts2.addRadioResource(new RadioResource());

        t.addBTS(bts1);
        t.addBTS(bts2);

        double expectedSignalLevel = bts1.maxSignalLevel() * 1.0/100 + bts2.maxSignalLevel() * 1.0/400;
        Assert.assertEquals(expectedSignalLevel, t.signalLevel(new Location(0,10)), 0.001);
    }
}
