import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args) {
//        1. Add a new class, `Exercise11`, to this project.
//        2. Add a `main` method.
//
//        4. Write a loop to sum values from `start` to `end` counting by the `increment`.
//        5. Print the result.
        Scanner console = new Scanner(System.in);
// Collect three integers from a user: `start`, `end`, and `increment`.
        System.out.print("Starting Number: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("Ending Number: ");
        int end = Integer.parseInt(console.nextLine());

        System.out.print("Increment: ");
        int increment = Integer.parseInt(console.nextLine());
// 4. Write a loop to sum values from `start` to `end` counting by the `increment`.
        int sum = 0;
        // sum all numbers between (7) start and end(16)
        for (int n = start; n <= end; n += increment) {
            sum += n;
        }
        System.out.println("Sum: " + sum);
    }
    }

