import java.util.Scanner;

public class Exercise13 {

    public static void main(String[] args) {
        // DOUBLE IT
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String phrase = console.nextLine();
// create  new string to store result
        String doubledPhrase = "";
        // 1. Write a loop that "doubles" each character in a user-entered word.
        for (int i = 0; i < phrase.length(); i++){
            char currentChar = phrase.charAt(i);
            doubledPhrase += currentChar;
            doubledPhrase += currentChar;
        }
        // You'll need a new string variable to store the result.
        // 2. Print the result.
        System.out.println("Doubled phrase: " + doubledPhrase);

        // Examples
        // ===============
        // "dog" -> "ddoogg"
        // "what?" -> "wwhhaatt??"
        // "" -> "" (empty string has nothing to double)
        // " " -> "  " (but whitespace should be doubled)
        // "open & shut" -> "ooppeenn  &&  sshhuutt"
        // "Eep" -> "EEeepp"
    }
}
