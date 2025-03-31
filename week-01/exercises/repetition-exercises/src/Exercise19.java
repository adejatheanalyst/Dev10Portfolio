import java.util.Scanner;

public class Exercise19 {
    public static void main(String[] args) {
        // INTERLEAVE
        Scanner console = new Scanner(System.in);

        System.out.print("First string: ");
        String first = console.nextLine();

        System.out.print("Second string: ");
        String second = console.nextLine();

        // 1. Write a loop to interleave two strings to form a new string.
        String result = "";
        int maxLength = Math.max(first.length(), second.length());
        // To interleave, during each loop take one character from the first string and add it to the result
        // and take one character from the second string and add it to the result.
        // If there are no more characters available, don't add characters.
        for (int i = 0; i < maxLength; i++){

            if (i < first.length()){
                result += first.charAt(i);

            } if (i< second.length()){
                result += second.charAt(i);
            }
        }
        // 2. Print the result.
System.out.println(result);
        // Examples
        // "abc", "123" -> "a1b2c3"
        // "cat", "dog" -> "cdaotg"
        // "wonder", "o" -> "woonder"
        // "B", "igstar" -> "Bigstar"
        // "", "huh?" -> "huh?"
        // "wha?", "" -> "wha?"
    }
}
