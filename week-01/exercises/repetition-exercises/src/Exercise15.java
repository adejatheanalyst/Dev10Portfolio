import java.util.Scanner;

public class Exercise15 {

    public static void main(String[] args) {
        // BOX
        //define the number of rows and columns for the box
        Scanner console = new Scanner(System.in);
        System.out.print("Enter Row number ");
        int rows = Integer.parseInt(console.nextLine());

        System.out.print("Enter Column number ");
        int columns = Integer.parseInt(console.nextLine());
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // loop for rows
        for (int i = 0; i < rows; i++ ){
            //loop for columns
            for( int j = 0; j < columns; j++) {
                System.out.print("#");
            } System.out.println();
        }
        // 2. Change the row and column limit to change the shape of the box.

        // Expected Output (5X5)
        // #####
        // #####
        // #####
        // #####
        // #####

        // (3X4)
        // ####
        // ####
        // ####
    }
}
