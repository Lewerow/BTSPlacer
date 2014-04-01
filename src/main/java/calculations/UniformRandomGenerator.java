package calculations;
import java.util.Random;

/**
 * Created by Ja on 29.03.14.
 */
public class UniformRandomGenerator extends RandomGenerator {
    int getInt(int min, int max)
    {
        Random r = new Random();
        return min + r.nextInt(max - min);
    }
    double getDouble(double min, double max)
    {
        Random r = new Random();
        return min + (r.nextDouble() * (max - min));
    }
}
