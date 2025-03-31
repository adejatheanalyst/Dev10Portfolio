import java.util.Scanner;

public class Exercise10 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Prompt the user for a phrase
        System.out.print("Enter a Phrase: ");
        String phrase = console.nextLine();

        // Call the whiteSpace method and store the result
        String result = whiteSpace(phrase);

        // Print the result (the phrase without whitespaces)
        System.out.println("Phrase without whitespace: " + result);
    }

    // Method that removes all whitespace from a string
    public static String whiteSpace(String phrase) {
        String result = "";

        // Loop through each character in the string
        for (int i = 0; i < phrase.length(); i++) {
            // If the character is not a whitespace, add it to the result string
            if (!Character.isWhitespace(phrase.charAt(i))) {
                result += phrase.charAt(i);
            }
        }

        // Return the result after the loop finishes
        return result;
    }
}
