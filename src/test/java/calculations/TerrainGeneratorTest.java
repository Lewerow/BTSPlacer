package calculations;

import java.util.LinkedList;
import java.util.List;

import algorithms.random.RandomGenerator;
import algorithms.random.TerrainGenerator;
import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import views.map.BTS;

import com.google.common.collect.Lists;

/**
 * Created by Ja on 30.03.14.
 */
public class TerrainGeneratorTest {
    @Test
    public void terrainWithNoBTSsHasNoSignal() {
        RandomGenerator gen = Mockito.mock(RandomGenerator.class);
        TerrainGenerator tGen = new TerrainGenerator();
        tGen.setRandomGenerator(gen);

        Terrain t = tGen.generateTerrain(null, null);
        Assert.assertEquals(t.getSignalLevel(PlacerLocation.getInstance(5, 5)), 0, 0.0001);
    }

    @Test
    public void terrainWithOneBTSsHasItsMaxSignalInOnePlace() {
        RandomGenerator gen = Mockito.mock(RandomGenerator.class);
        TerrainGenerator tGen = new TerrainGenerator();
        tGen.setRandomGenerator(gen);

        BTS bts = new BTS(null, BtsType.CIRCULAR);
        BTS spyBTS = Mockito.spy(bts);
        List<BTS> btsList = new LinkedList<BTS>();
        btsList.add(spyBTS);

        Mockito.when(spyBTS.getMaxSignalLevel()).thenReturn(100.0);
        Mockito.when(gen.getDouble(Mockito.any(Double.class))).thenReturn(5.0);

        Terrain t = tGen.generateTerrain(btsList, Lists.<SubscriberCenter>newArrayList());
        double baseX = PlacerLocation.getWroclawLocation().getX();
        double baseY = PlacerLocation.getWroclawLocation().getY();
        Assert.assertEquals(t.getSignalLevel(PlacerLocation.getInstance(baseX + 5d, baseY + 5d)),
                100.0, 0.0001);
    }
}
