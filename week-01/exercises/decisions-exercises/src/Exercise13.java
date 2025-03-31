import java.util.Scanner;

public class Exercise13 {

    public static void main(String[] args) {
        // NEEDLE & HAYSTACK
        Scanner console = new Scanner(System.in);

        System.out.print("Needle: ");
        String needle = console.nextLine();

        System.out.print("Haystack: ");
        String haystack = console.nextLine();

        // 1. Given two string variables: needle and haystack, determine if haystack contains needle.
        if (haystack.contains(needle)) {
            System.out.println("Yes, the haystack contains the needle. ");

        // Examples
        // needle  haystack contains?
        //     an      bean      yes
        //    een      bean       no
        //    ury   Mercury      yes
        //    ury     curry       no
        //    mer   Mercury       no (case sensitive)
        // 2. As a stretch goal, display the location (index) of needle in haystack.
        int index = haystack.indexOf(needle);
        System.out.println("The needle starts at index: " + index);}
        else {
            System.out.println("No, the haystack does not contain the needle.");
        }

    }
}
