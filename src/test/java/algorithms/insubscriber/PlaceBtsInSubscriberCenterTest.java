package algorithms.insubscriber;

import calculations.PlacerLocation;
import calculations.SubscriberCenter;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.util.List;

public class PlaceBtsInSubscriberCenterTest {

    @Test
    public void shouldSortSubscribersDescendingByRequiredSignal() {
        //given
        PlaceBtsInSubscriberCenter algorithm = new PlaceBtsInSubscriberCenter();
        List<SubscriberCenter> subscribers = Lists.newArrayList();

        SubscriberCenter subscriber1 = createNewSubscriberCenter(100);
        SubscriberCenter subscriber2 = createNewSubscriberCenter(200);
        SubscriberCenter subscriber3 = createNewSubscriberCenter(300);
        SubscriberCenter subscriber4 = createNewSubscriberCenter(400);

        subscribers.add(subscriber1);
        subscribers.add(subscriber3);
        subscribers.add(subscriber2);
        subscribers.add(subscriber4);

        Assertions.assertThat(subscribers.get(0).getRequiredSignal()).isEqualTo(100);
        Assertions.assertThat(subscribers.get(1).getRequiredSignal()).isEqualTo(300);
        Assertions.assertThat(subscribers.get(2).getRequiredSignal()).isEqualTo(200);
        Assertions.assertThat(subscribers.get(3).getRequiredSignal()).isEqualTo(400);

        //when
        List<SubscriberCenter> sortedSubscribers = algorithm.sortSubscriberCenter(subscribers);

        //then
        Assertions.assertThat(sortedSubscribers.get(0).getRequiredSignal()).isEqualTo(400);
        Assertions.assertThat(sortedSubscribers.get(1).getRequiredSignal()).isEqualTo(300);
        Assertions.assertThat(sortedSubscribers.get(2).getRequiredSignal()).isEqualTo(200);
        Assertions.assertThat(sortedSubscribers.get(3).getRequiredSignal()).isEqualTo(100);
    }

    private SubscriberCenter createNewSubscriberCenter(double requiredSignal) {
        return new SubscriberCenter(requiredSignal, PlacerLocation.getInstance(25d, 25d), 0.6, 0.7);
    }

}