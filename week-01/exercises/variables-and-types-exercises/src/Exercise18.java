public class Exercise18 {

    public static void main(String[] args) {

        int gemCount = 12;
        double velocity = 33.654;
        boolean isShiny = true;

        // 1. Print the variable values above with String labels. Use concatenation.
        String message = "Gem Count: " + gemCount + "\n";
        message += "Velocity: " + velocity + "\n";
        message += "Is Shiny: " + isShiny;
        System.out.println(message);

        // Expected Output:
        // Gem Count: 12
        // Velocity: 33.654
        // Is Shiny: true
    }
}
