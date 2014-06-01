package algorithms.random;

import java.util.Random;

/**
 * Created by Ja on 29.03.14.
 */
public class UniformRandomGenerator extends RandomGenerator {
	private final Random r = new Random();

	@Override
	public int getInt(int min, int max) {
		return min + r.nextInt(max - min);
	}

	@Override
	public double getDouble(double min, double max) {
		return min + (r.nextDouble() * (max - min));
	}
}
