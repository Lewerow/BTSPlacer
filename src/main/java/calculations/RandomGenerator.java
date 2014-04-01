package calculations;
/**
 * Created by Ja on 29.03.14.
 */
public abstract class RandomGenerator {
    int getInt()
    {
        return getInt(Integer.MAX_VALUE);
    }
    int getInt(int max)
    {
        return getInt(0, max);
    }
    abstract int getInt(int min, int max);

    double getDouble()
    {
        return getDouble(Double.MAX_VALUE);
    }
    double getDouble(double max)
    {
        return getDouble(0, max);
    }

    abstract double getDouble(double min, double max);
}
