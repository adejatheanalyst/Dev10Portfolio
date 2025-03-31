import java.util.Scanner;

public class Exercise02 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a whole number: ");
        String input = console.nextLine();

        int value = Integer.parseInt(input);
if (value % 2 == 0) {
    System.out.println("You entered an Even Number. ");
}
        // 1. Add an if statement that determines if value is even.
        // 2. If it is, print a message.
        // Don't worry about bad input. e.g. if the user enters "pppffghht".
    }
}
