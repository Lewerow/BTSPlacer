import junit.framework.Assert;
import org.junit.Test;
import java.util.Iterator;
import static org.mockito.Mockito.*;
/**
 * Created by Ja on 20.03.14.
 */


public class BTSTester {

    @Test
    public void sanityCheckForJUnit()
    {
        Assert.assertEquals(1,1);
    }

    @Test
    public void sanityCheckForMockito()
    {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("Mockito");
        String res = i.next() + " " + i.next();
        Assert.assertEquals(res, "Hello Mockito");
    }
}
