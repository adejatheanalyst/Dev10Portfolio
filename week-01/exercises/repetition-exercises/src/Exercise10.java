import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Start: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("End: ");
        int end = Integer.parseInt(console.nextLine());

        // 1. Write a loop to sum all numbers between start and end.
        int sum = 0;
        // sum all numbers between (7) start and end(16)
        for (int n= start; n<= end; n++){
            sum += n;
        }
        System.out.println("Sum: " + sum);
        // 2. Print the result.
    }
}
