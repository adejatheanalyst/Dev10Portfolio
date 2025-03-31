import java.util.Scanner;

public class Exercise09 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter Row number ");
        int rows = Integer.parseInt(console.nextLine());

        System.out.print("Enter Column number ");
        int columns = Integer.parseInt(console.nextLine());
        // 2. Call your method in various ways to test it here.
//        printBox(5,5);
//        printBox(3, 4);
        printBox(rows, columns);
    }

    // 1. Create a method.
    // Name: printBox
    // Inputs: int, int
    // Output: void // no need for return value
    // Description: prints a visual box to the console. The first parameter is the number of rows to print.
    // The second parameter is the number of columns.
    // See repetition Exercise15.
    public static void printBox(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            //loop for columns
            for (int j = 0; j < columns; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}

    // Expected Output (5 rows, 5 columns)
    // #####
    // #####
    // #####
    // #####
    // #####

    // (3 rows, 4 columns)
    // ####
    // ####
    // ####

