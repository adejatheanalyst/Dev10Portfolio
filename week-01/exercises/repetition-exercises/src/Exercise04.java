import java.util.Scanner;

public class Exercise04 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Starting Number: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("Ending Number: ");
        int end = Integer.parseInt(console.nextLine());

        System.out.print("Increment: ");
        int increment = Integer.parseInt(console.nextLine());

        // 1. Write a loop that uses three values provided by a user to control the loop.
        for (int i = start;
        i <= end; i += increment){
            System.out.println(i);
        }
        // start - the starting value
        // end - controls the loop condition, the loop continues while the value is less than or equal to end
        // increment - the number to add after each loop

    }
}
