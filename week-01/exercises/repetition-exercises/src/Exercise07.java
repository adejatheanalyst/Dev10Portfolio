import java.util.Scanner;

public class Exercise07 {

    public static void main(String[] args) {
        // REMOVE WHITESPACE
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase and I'll remove the whitespace: ");
        String phrase = console.nextLine();

        // 1. Write code to remove whitespace from a user-entered phrase.
        // Hint: the Character.isWhitespace method may be useful.
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#isWhitespace(char)

        String result = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (!Character.isWhitespace(phrase.charAt(i)))
            result += phrase.charAt(i);
        }

        System.out.println("Your phrase without whitespace is: " + result);
    }
}
