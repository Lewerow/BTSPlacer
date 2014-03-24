import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ja on 24.03.14.
 */
public class BTSTest {

    BTS bts;

    @Before
    public void setup()
    {
        bts = new BTS();
    }

    @Test
    public void atStartupBTSShallNotContainBBResources()
    {
        Assert.assertEquals(bts.getBBResourceCount(), 0);
    }

    @Test
    public void afterAddingSingleBBResourceBTSContainsOneBBResource()
    {
        bts.addBBResource(new BasebandResource());
        Assert.assertEquals(bts.getBBResourceCount(), 1);
    }

    @Test
    public void afterAddingTenBBResourcesBTSContainsTenBBResources()
    {
        for(int i = 0; i < 10; ++i)
            bts.addBBResource(new BasebandResource());

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
            bts.addBBResource(new BasebandResource());

        Assert.assertEquals(bts.getRadioResourceCount(), 10);
        Assert.assertEquals(bts.getBBResourceCount(), 10);
    }
}
