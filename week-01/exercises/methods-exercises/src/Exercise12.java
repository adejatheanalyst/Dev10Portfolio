import java.util.Scanner;

public class Exercise12 {


    // 1. Create a method.
    // Name: readRequiredString
    // Inputs: String
    // Output: String
    // Description: prompts a user to enter a required string and returns their validated input.
    // The parameter is the message displayed to the user.
    //
    // See the readRequiredString implementation in the methods lesson.
    // You can definitely improve it. Make sure you don't allow blank input. Checking the length() is not enough.
public static String readRequiredString (String input){
    Scanner console = new Scanner(System.in);
    String output;
    do {
        System.out.print(input);
        output = console.nextLine();
        if (output.isBlank()){ //output == null || output.trim().isEmpty())
            System.out.println("Input cannot be blank. Please enter a valid string");
        }
    } while (output.isBlank());
    return output;
}
    // 2. Create a method.
    // Name: printNounPhrase
    // Inputs: none
    // Output: none
    // Description: prints an adjective + noun phrase to the console based on user input.
    // Internally, prompts a user for an adjective and a noun with readRequiredString.
    // Concatenates adjective and noun and prints it to the console.
public static void printNounPhrase(){
    String adjective = readRequiredString("Enter a Adjective ");
    String noun = readRequiredString("Enter a noun ");

    System.out.println(" Your Phrase is: " + adjective + " " + noun + " ");
}
    public static void main(String[] args) {
        // 3. Uncomment the code below and confirm it works.
         printNounPhrase();
         printNounPhrase();
         printNounPhrase();
    }
}
