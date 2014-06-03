package serialization;

import calculations.SubscriberCenter;
import com.google.common.collect.Lists;
import views.map.BTS;

import java.util.List;

/**
 * Created by Vortim on 2014-06-03.
 */
public class DataContainer {

    private List<BTS> btss = Lists.newArrayList();
    private List<SubscriberCenter> subscriberCenters = Lists.newArrayList();

    public DataContainer(List<BTS> btss, List<SubscriberCenter> subscriberCenters){
        this.btss = btss;
        this.subscriberCenters = subscriberCenters;
    }

    public List<SubscriberCenter> getSubscriberCenters() {
        return subscriberCenters;
    }

    public List<BTS> getBtss() {
        return btss;
    }
}
