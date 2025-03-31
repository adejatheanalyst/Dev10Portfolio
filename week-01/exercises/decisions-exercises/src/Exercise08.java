import java.util.Scanner;

public class Exercise08 {
    public static void main(String[] args) {
        // OPPOSITES
        // Given a word, print its opposite.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = console.nextLine();
        String opposite = null;

        // 1. Add at least two more opposites by adding `else if` clauses.
        if (word.equalsIgnoreCase("high")) {
            opposite = "low";
        } else if (word.equalsIgnoreCase("cold")) {
            opposite = "hot";
        } else if (word.equalsIgnoreCase("little")) {
            opposite = "big";
        } else if (word.equalsIgnoreCase("pretty")) {
            opposite = "ugly";
        } else if (word.equalsIgnoreCase("bad")) {
            opposite = "good";
        } else if (word.equalsIgnoreCase("crazy")) {
            opposite = "sane";}

        if (opposite == null) {
            System.out.printf("I don't have an opposite for %s.%n", word);
        } else {
            System.out.printf("The opposite of %s is %s.%n", word, opposite);
        }
    }
}
