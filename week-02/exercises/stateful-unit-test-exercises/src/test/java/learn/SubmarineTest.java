package learn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmarineTest {

    Submarine submarine = new Submarine(10, 0.0, 1);

    @Test
    void shouldHaveCorrectDepthAfter3Dives() {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        assertEquals(9.0, submarine.getDepthInMeters(), 0.001);
    }

    @Test
    void shouldHaveCorrectPressureAfter3Dives() {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        // 1.0 at sea level plus 1.0 * 0.9
        assertEquals(1.9, submarine.getPressureInAtmospheres(), 0.001);
    }

    // 1. Create one or more tests to confirm `dive` is working properly.
    @Test
    void diveShouldBeWorkingProperly() {
        submarine.dive(); // First dive
        submarine.dive(); // Second dive
        submarine.dive(); // Third dive
        submarine.dive();
        submarine.dive();// Try to dive beyond maxDepth
    }

    // 2. Create a test to assert the submarine can't go deeper than the max depth.
    // (Be sure to use more than one max depth.)
    @Test
    void shouldNotGoDeeperThanDepth() {
        submarine.dive(); // First dive
        submarine.dive(); // Second dive
        submarine.dive(); // Third dive
        submarine.dive();
        submarine.dive();
        submarine.dive();// Try to dive beyond maxDepth
        assertEquals(9.0, submarine.getDepthInMeters(), 0.001);
    }

    // 3. Create one or more tests to confirm `surface` is working properly.
    @Test
    void surfaceShouldBeWorkingProperly() {
        //arrange phase
        submarine.dive();
        submarine.dive();
        submarine.dive();
        submarine.dive();
        //Act phase for test
        submarine.surface();
        submarine.surface();
        //assert phase;
        assertEquals(2.0, submarine.getDepthInMeters(), 0.001);
    }

    // 4. Create a test to assert the submarine can't go above sea level.
    @Test
    void shouldNotBeAbleToGoAboveSeaLevel() {
        //Arrange
        submarine.dive();
        submarine.dive();
        submarine.dive();
        submarine.dive();
        submarine.dive();
        //Act Phase
        submarine.surface();
        submarine.surface();
        submarine.surface();
        submarine.surface();
        //Assert Phase
        assertEquals(0.0, submarine.getDepthInMeters(), 0.001);
    }

    // 5. Create one or more tests to confirm `getPressureInAtmospheres` is working properly.
    @Test
    void getPressureShouldBeWorkingProperly(){
       submarine.dive(); // First dive
         submarine.dive();
        submarine.dive();
        submarine.dive(); // Second dive
        System.out.println("Current pressure: " + submarine.getPressureInAtmospheres() + " atmospheres.");
        submarine.dive(); // Third dive
        System.out.println("Current pressure: " + submarine.getPressureInAtmospheres() + " atmospheres.");
}
}
