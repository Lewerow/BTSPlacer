package calculations;

/**
 * Created by Ja on 24.03.14.
 */

/*
 * @brief FSPs, FBBs...
 * 
 * @invariants - capacity must be >= 0
 */
public class BasebandResource {
	private final double capacity;

	public BasebandResource(double capacity) {
		assert capacity >= 0 : "Baseband resource capacity cannot be negative";
		this.capacity = capacity;
	}

	public double getCapacity() {
		return capacity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BasebandResource) {
			BasebandResource other = (BasebandResource) obj;
			return capacity == other.getCapacity();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("BasebandResource(Capacity: %f)", capacity);
	}
}
