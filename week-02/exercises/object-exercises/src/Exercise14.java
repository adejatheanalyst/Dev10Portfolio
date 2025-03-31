public class Exercise14 {

    public static void main(String[] args) {

        // 1. Uncomment the code below.
        // 2. Fix any errors by editing the Hero class.
        // 3. Confirm the output matches Expected Output.

        Power levitation = new Power("Levitation");
        Power flight = new Power("Flight");
        Power blastPower = new Power("Blast Power");

        Power[] visionPowers = new Power[]{levitation, blastPower};

        Hero[] heroes = {
                new Hero("Vision", visionPowers),
                new Hero("Scarlet Witch", new Power[]{levitation, flight, new Power("Necromancy")}),
                new Hero("Bumblebee", new Power[]{blastPower, flight})
        };

        for (Hero h : heroes) {
            System.out.println(h.toLine());

        }

//        Expected Output:
//        Vision: Levitation,Blast Power
//        Scarlet Witch: Levitation,Flight,Necromancy
//        Bumblebee: Blast Power,Flight
    }
}
