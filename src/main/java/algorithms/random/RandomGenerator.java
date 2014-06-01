package algorithms.random;

/**
 * Created by Ja on 29.03.14.
 */
public abstract class RandomGenerator {

	public int getInt() {
		return getInt(Integer.MAX_VALUE);
	}

	public int getInt(int max) {
		return getInt(0, max);
	}

	public abstract int getInt(int min, int max);

	public double getDouble() {
		return getDouble(Double.MAX_VALUE);
	}

	public double getDouble(double max) {
		return getDouble(0, max);
	}

	public abstract double getDouble(double min, double max);
}
