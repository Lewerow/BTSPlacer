package algorithms;

import calculations.SubscriberCenter;
import calculations.Terrain;

import java.util.List;

/**
 * Created by Vortim on 2014-06-01.
 */
public interface Algorithm {
    
    public Terrain regenerateTerrain(Terrain currentTerrain);

    public void setBtsCount(int btsCount);

    public void setSubscriberCenterCount(int subscriberCenterCount);
}
