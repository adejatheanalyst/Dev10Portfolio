import java.util.Scanner;

public class MethodsWarmup {
// create method to count vowel input string output int.
    public static int vowelCount(String input) {
        int count = 0;
        // loop through each character to make sure its a vowel
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i); // create a char variable to make it easier to check
            // check if the character is lower case vowel
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++; // add to the count if its a vowel
            }
        }
        return count; // return the number of vowels
    }
    public static boolean containsVowels(String input){
       return vowelCount(input) > 0; // return the count of vowels if the vowel count is greater than one

    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a phrase: ");
        String input = console.nextLine();
        // call methods

        int vowelCountResult = vowelCount(input);
        boolean hasVowels = containsVowels(input);

        System.out.println("Does the phrase contain vowels? " + hasVowels);
        System.out.println("Amount of vowels in the phrase: " + vowelCountResult);

    }















}
// contains vowels method
