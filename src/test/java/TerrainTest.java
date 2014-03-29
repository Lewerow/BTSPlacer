import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ja on 29.03.14.
 */
public class TerrainTest {

    private Terrain t;
    private Location l1, l2;

    @Before
    public void setUp()
    {
        t = new Terrain();
        l1 = new Location(1, 20);
        l2 = new Location(5, 20);
    }

    @Test
    public void terrainCanCalculateDistanceBetweenLocations()
    {
        Assert.assertEquals(t.distance(l1, l2), 4, 0.00001);
    }

    @Test
    public void signalReductionRatioToSelfIs0()
    {
        Assert.assertEquals(t.signalReduction(l1, l1), 0, 0.0001);
    }
}
