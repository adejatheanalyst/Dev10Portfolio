import java.util.Scanner;

public class PrintLowercaseVowels {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();

        for (int i = 0; i < phrase.length(); i++) {
//
            char index = phrase.toLowerCase().charAt(i);
            if (index == 'a' || index == 'e' ||index == 'i' ||index == 'o' ||index == 'u') {
                System.out.println(index);
            }

        }
    }
}