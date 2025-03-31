import java.util.Scanner;
public class Exercise14 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        // 1. Collect a phrase from a user via the console.
        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();
        // 2. Count the number of digits in the phrase.
        int digitCount = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isDigit(phrase.charAt(i))) {
                digitCount++;
            }
        }
        // Hint: Character.isDigit
        // 3. Print the result.
        System.out.println("Number of digits in the phrase: " + digitCount);
    }
}
