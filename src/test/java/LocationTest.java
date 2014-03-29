import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Ja on 29.03.14.
 */
public class LocationTest {
    @Test
    public void singleLocationIsOnPlaneXFirstYSecond()
    {
        Location l = new Location(10, 15);

        Assert.assertEquals(l.x(), 10, 0.001);
        Assert.assertEquals(l.y(), 15, 0.001);
    }

    @Test
    public void distanceBetweenSelfIsZero()
    {
        Location l = new Location(10, 10);
        Assert.assertEquals(l.distance(l), 0, 0.0001);
    }

    @Test
    public void distanceBetweenSameXValueIsYValueDifference()
    {
        Location l1 = new Location(10, 20);
        Location l2 = new Location(10, 30);

        Assert.assertEquals(l1.distance(l2), 10, 0.0001);
    }

    @Test
    public void distanceABIsEqualToDistanceBA()
    {
        Location l1 = new Location(10, 20);
        Location l2 = new Location(10, 30);

        Assert.assertEquals(l1.distance(l2), l2.distance(l1), 0.0001);
    }
}
