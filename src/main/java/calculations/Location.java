package calculations;

/**
 * Created by Ja on 29.03.14.
 */
public class Location {

	private final double x;
	private final double y;

	public static Location getInstance(double x, double y) {
		return new Location(x, y);
	}

	private Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double cartesianDistance(Location l2) {
		return Math.sqrt(Math.pow(this.x - l2.x, 2) + Math.pow(this.y - l2.y, 2));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location otherLocation = (Location) obj;
			return otherLocation.getX() == x && otherLocation.getY() == y;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Location( %f ; %f )", x, y);
	}

}
