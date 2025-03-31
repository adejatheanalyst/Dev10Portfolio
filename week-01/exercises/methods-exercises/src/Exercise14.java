import java.util.Scanner;

public class Exercise14 {
    public static void main(String[] args) {
        String firstName = "";
        String lastName = "";
        int towns = 0;
        int music = 0;

        firstName = readRequiredString("What's your first name?: ");
        lastName = readRequiredString("What's your last name?: ");

        // Uncomment this line to get the number of pillows from the user
        towns = readInt("How many towns/cities have you lived in?: ");
        music = readInt("How many musical instruments can you play?: ");

        // Print the result
        System.out.printf("%s  %s has lived in %d cities and plays %d instruments.%n", firstName, lastName, towns, music);


    }
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
    // read required String method

     */
    public static String readRequiredString (String input){
        Scanner console = new Scanner(System.in);
        String output;
        do {
            System.out.print(input);
            output = console.nextLine();
            if (output == null || output.trim().isEmpty()){
                System.out.println("Input cannot be blank. Please enter a valid string");
            }
        } while (output == null || output.trim().isEmpty());
        return output;
    }
// get int string method
    public static int readInt(String prompt) {
        Scanner console = new Scanner(System.in);
        String input;
        int result = 0;
        boolean isValid = false;
        do {
            System.out.println(prompt);
            input = console.nextLine();
            // check if input is numeric
            if (isNumeric(input)){
                result = Integer.parseInt(input);
                isValid = true;
            } else{
                System.out.println("Invalid input. Please enter a whole number.");
            }
        } while (!isValid);
        // Parse the string into an integer and return it
        return result;
    } // method to find if input is numeric
    public static boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

}
