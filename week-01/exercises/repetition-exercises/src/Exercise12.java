import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();

        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // going to use true or false to see of x is in phrase
        boolean foundX = false;
        for (int i = 0; i < phrase.length(); i++){
            if (phrase.charAt(i) == 'x' || phrase.charAt(i) == 'X'){
                foundX = true;
                break;
            }
        }
        // 2. Print a message for both finding and not finding the `x`.
        if (foundX) {
            System.out.println("The phrase contains the letter 'x'.");
        } else {
            System.out.println("The phrase does not contain the letter 'x'.");}
    }
}
