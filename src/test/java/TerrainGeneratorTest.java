import junit.framework.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import org.hamcrest.Matchers;
/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGeneratorTest {
    @Test
    public void terrainWithNoBTSsHasNoSignal()
    {
        RandomGenerator gen = Mockito.mock(RandomGenerator.class);
        TerrainGenerator tGen = new TerrainGenerator(gen);

        Terrain t = tGen.generateTerrain(10, 10, null);
        Assert.assertEquals(t.signalLevel(new Location(5, 5)), 0, 0.0001);
    }

    @Test
    public void terrainWithOneBTSsHasItsMaxSignalInOnePlace()
    {
        RandomGenerator gen = Mockito.mock(RandomGenerator.class);
        TerrainGenerator tGen = new TerrainGenerator(gen);

        BTS bts = new BTS(null);
        BTS spyBTS = Mockito.spy(bts);
        List<BTS> btsList = new LinkedList<BTS>();
        btsList.add(spyBTS);

        Mockito.when(spyBTS.maxSignalLevel()).thenReturn(100.0);
        Mockito.when(gen.getDouble(Mockito.any(Double.class))).thenReturn(5.0);

        Terrain t = tGen.generateTerrain(10, 10, btsList);
        Assert.assertEquals(t.signalLevel(new Location(5, 5)), 100.0, 0.0001);
    }
}
