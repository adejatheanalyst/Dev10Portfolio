package learn;

/**
 * An underwater, submersible vehicle.
 * Includes two behaviors.
 * dive: go down a little deeper under water to a maximum depth
 * surface: come up a little shallower to a minimum depth of sea level
 * <p>
 * The submarine's current depth and pressure are available via getters.
 */
public class Submarine {

    private final double maxDepth;
    private double depthInMeters;
    private final double minDepth;
    private double pressure;

    public Submarine(double maxDepth, double minDepth, double pressure) {
        this.maxDepth = maxDepth;
        this.minDepth = minDepth;
        this.pressure = pressure;
    }

    public double getMinDepth() {return minDepth;}

    public double getDepthInMeters() {
        return depthInMeters;
    }

    public void dive() {
        // 1. Each dive should increase the depth by 3 meters.
        // Depth cannot exceed maxDepth.
        if (depthInMeters + 3 <= maxDepth){
            depthInMeters += 3;// increase depth by 3 meters
            System.out.println("Submarine is diving... Current depth: " + depthInMeters + " meters.");
        } else {
            System.out.println("You have reached the max depth. You cannot dive any further!");
        }

    }

    public void surface() {
        // 2. Each surface should decrease the depth by 5 meters.
        // Minimum depth is 0.0 (sea level).
        if (depthInMeters == minDepth) {
            System.out.println("You have reached sea level!");
        } else if (depthInMeters - 5 >= minDepth) {
            depthInMeters -= 5;
            System.out.println("Submarine is surfacing... Current depth: " + depthInMeters + " meters.");
        }else {
            depthInMeters = minDepth;
            System.out.println("You are at sea level!");
        }

        }


    public double getPressureInAtmospheres() {
        // 3. At sea level, pressure is 1 atmosphere.
        // Pressure increases by 1 atmosphere for every 10 meters.
        return 1 + (depthInMeters / 10.0);
    }

}
