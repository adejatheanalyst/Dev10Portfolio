public class Exercise15 {

    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public static void main(String[] args) {

        // 2. Instantiate your three favorite super heroes with appropriate powers.
        Power levitation = new Power("Levitation");
        Power flight = new Power("Flight");
        Power blastPower = new Power("Blast Power");

        Hero[] heroes = {
                new Hero("Vision", new Power[]{levitation, blastPower}),
                new Hero("Scarlet Witch", new Power[]{levitation, flight, new Power("Necromancy")}),
                new Hero("Bumblebee", new Power[]{blastPower, flight})
        };
        
        // 3. Use the `toLine` method to print each hero's details to the console.
        for (Hero hero : heroes) {
            System.out.println(hero.toLine());
        }
    }
}
