import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ja on 24.03.14.
 */
public class BTSTest {

    Location btsLocation = new Location(0,0);
    Location l = new Location(0, 10);
    Terrain t = new Terrain();
    BTS bts = new BTS(btsLocation);

    @Test
    public void atStartupBTSShallNotContainBBResources()
    {
        Assert.assertEquals(bts.getBBResourceCount(), 0);
    }

    @Test
    public void afterAddingSingleBBResourceBTSContainsOneBBResource()
    {
        bts.addBBResource(new BasebandResource(0));
        Assert.assertEquals(bts.getBBResourceCount(), 1);
    }

    @Test
    public void afterAddingTenBBResourcesBTSContainsTenBBResources()
    {
        for(int i = 0; i < 10; ++i)
            bts.addBBResource(new BasebandResource(0));

        Assert.assertEquals(bts.getBBResourceCount(), 10);
    }

    @Test
    public void atStartupBTSShallNotContainRadioResources()
    {
        Assert.assertEquals(bts.getRadioResourceCount(), 0);
    }

    @Test
    public void afterAddingSingleRadioResourceBTSContainsOneRadioResource()
    {
        bts.addRadioResource(new RadioResource());
        Assert.assertEquals(bts.getRadioResourceCount(), 1);
    }

    @Test
    public void afterAddingTenRadioResourceBTSContainsTenRadioResource()
    {
        for(int i = 0; i < 10; ++i)
            bts.addRadioResource(new RadioResource());

        Assert.assertEquals(bts.getRadioResourceCount(), 10);
    }

    @Test
    public void afterAddingTenRadioResourcesAndTenBBResourcesBTSContainsTenOfEach()
    {
        for(int i = 0; i < 10; ++i)
            bts.addRadioResource(new RadioResource());

        for(int i = 0; i < 10; ++i)
            bts.addBBResource(new BasebandResource(0));

        Assert.assertEquals(bts.getRadioResourceCount(), 10);
        Assert.assertEquals(bts.getBBResourceCount(), 10);
    }

    @Test
    public void BTSBasebandCapacityIsEqualToSumOfBBResourceCapacities()
    {
        bts.addBBResource(new BasebandResource(10));
        bts.addBBResource(new BasebandResource(30));
        bts.addBBResource(new BasebandResource(50));
        Assert.assertEquals(bts.getBBCapacity(), 90, 0.01);
    }

    @Test
    public void BTSWithoutRadiosEmitsNoSignal()
    {
        bts.addBBResource(new BasebandResource(50));
        Assert.assertEquals(bts.maxSignalLevel(), 0, 0.0001);
    }

    @Test
    public void BTSWithoutBBResourcesEmitsNoSignal()
    {
        bts.addRadioResource(new RadioResource());

        Assert.assertEquals(bts.maxSignalLevel(), 0, 0.0001);
    }

    @Test
    public void signalLevelAtBTSLocationIsEqualToItsBBCapacityTimenumberOfRadioModules()
    {
        bts.addBBResource(new BasebandResource(10));
        bts.addRadioResource(new RadioResource());
        bts.addRadioResource(new RadioResource());

        Assert.assertEquals(bts.maxSignalLevel(), 20, 0.0001);
    }
}
