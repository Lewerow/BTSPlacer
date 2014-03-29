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
}
