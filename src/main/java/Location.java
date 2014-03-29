/**
 * Created by Ja on 29.03.14.
 */
public class Location {
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double x;
    private double y;

    public double x() {
        return x;
    }
    public double y() {
        return y;
    }

    public double distance(Location l2)
    {
        // Cartesian distance
        return Math.sqrt(Math.pow(this.x - l2.x, 2) + Math.pow(this.y - l2.y, 2));
    }
}
