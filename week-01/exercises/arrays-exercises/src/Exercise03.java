public class Exercise03 {
    public static void main(String[] args) {
        String[] commonlyMisspelledWords = {"indict", "fiery", "misspell", "comparsion", "perseverance"};

        for (int i = 0; i < commonlyMisspelledWords.length; i++) {
            System.out.println(commonlyMisspelledWords[i]);
        }

        // One of the commonlyMisspelledWords is misspelled.
        // (IntelliJ may help here. It highlights misspelled words.)
        // 1. Change it to the correct spelling. Don't alter the array literal declaration. Set the value by index.
        commonlyMisspelledWords[3] = "comparison";

        // 2. Loop a second time and confirm all five words are spelled correctly.
        System.out.println("\nCorrected words: ");
        for (int i = 0; i < commonlyMisspelledWords.length; i++) {
            System.out.println(commonlyMisspelledWords[i]);
        }
    }
}
