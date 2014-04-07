package calculations;

/**
 * Created by Ja on 24.03.14.
 */
public class RadioResource {

    private int range;

    int getRange()
    {
        return range;
    }

    RadioResource(int newRange)
    {
        range = newRange;
    }

	@Override
	public boolean equals(Object obj) {
        if (obj instanceof Location) {
            RadioResource rhs = (RadioResource) obj;
            return rhs.getRange() == range;
        }
        return false;
	}

	@Override
	public String toString() {
		return "RadioResource()";
	}
}
