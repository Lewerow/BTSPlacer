/**
 * Created by Ja on 24.03.14.
 */

/*
 * @brief FSPs, FBBs...
 * @invariants
 * - capacity must be >= 0
 */
public class BasebandResource {
    private double capacity;

    BasebandResource(double capacity)
    {
        assert capacity >= 0 : "Baseband resource capacity cannot be negative";
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }
}
