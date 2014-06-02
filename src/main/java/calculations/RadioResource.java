package calculations;

/**
 * Created by Ja on 24.03.14.
 */
public class RadioResource {

    private double range;

    public double getRange()
    {
        return range;
    }

    public RadioResource(double newRange)
    {
        range = newRange;
    }

	@Override
	public boolean equals(Object obj) {
        if (obj instanceof RadioResource) {
            RadioResource rhs = (RadioResource) obj;
            return range == rhs.getRange();
        }
        return false;
	}

	@Override
	public String toString() {
		return "RadioResource()";
	}
}
