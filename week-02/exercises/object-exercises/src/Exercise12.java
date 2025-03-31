public class Exercise12 {

    public static void main(String[] args) {

        // 1. Uncomment the code below.
        // 2. Fix any errors by editing the Power class.
        // 3. Confirm the output matches Expected Output.

        Power[] powers = {
                new Power("Flight"),
                new Power("Invisibility"),
                new Power("Strength"),
                new Power("Laser Beam Eyes"),
                new Power("Shapeshifting")
        };

        for (int i = 0; i < powers.length; i++) {
            Power p = powers[i];
            System.out.println(p.getName());
        }

//        Expected Output:
//        Flight
//        Invisibility
//        Strength
//        Laser Beam Eyes
//        Shapeshifting
    }
}

