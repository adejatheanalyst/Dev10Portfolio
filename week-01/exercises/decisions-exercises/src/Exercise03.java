import java.util.Scanner;

public class Exercise03 {

    public static void main(String[] args) {
        // 1. Change the code to accept input from the user.
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a whole number: ");
        String input = console.nextLine();
        // Use a Scanner and parse the input with Integer.parseInt.
        int value = Integer.parseInt(input);
        if (value >= 10 && value < 26) {
            System.out.println("Value is in the acceptable range.");
        }
    }
}
