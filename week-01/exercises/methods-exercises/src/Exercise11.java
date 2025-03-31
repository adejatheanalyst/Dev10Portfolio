import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args) {
        String name = null;
        int pillowCount = 0;

        // Call the readString method to get the user's name
        name = readString("What's your name?: ");

        pillowCount = readInt("How many pillows do you sleep with?: ");

        // Print the result
        System.out.printf("%s sleeps with %d pillows.%n", name, pillowCount);
    }

    // Method to prompt the user with a string and return the input
    public static String readString(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        return console.nextLine();
    }

    // Method to prompt the user with a string, read the input, and return it as an int
    public static int readInt(String prompt) {
        // Parse the string into an integer and return it
        return Integer.parseInt(readString(prompt));
    }
}
