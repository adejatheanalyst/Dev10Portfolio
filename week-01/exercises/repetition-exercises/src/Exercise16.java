import java.util.Scanner;

public class Exercise16 {

    public static void main(String[] args) {
        // BORDER BOX
        // 1. Use nested loops to print a box in the console with a different character for the border.
        // One loop should represent rows and the other should represent columns.
        // The border character should be different from the internal box character.
        // 2. Change the row and column limit to change the shape of the box.
//        Scanner console = new Scanner(System.in);
//        System.out.print("Enter Row number ");
//        int rows = Integer.parseInt(console.nextLine());
//
//        System.out.print("Enter Column number ");
//        int columns = Integer.parseInt(console.nextLine());
        int rows = 5;
        int columns = 5;
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // loop for rows
        for (int r = 0; r < rows; r++ ) {
            //loop for columns
            for (int c = 0; c < columns; c++) {
                if (c == 0 || r == 0 || r == rows - 1 || c == columns - 1) {
                    System.out.print("*");
                } else {
                    System.out.print("#");
                }
            }System.out.println();
            // Expected Output (5X5)
            // *****
            // *###*
            // *###*
            // *###*
            // *****

            // (3X4)
            // ****
            // *##*
            // ****

            // (2X2)
            // **
        } // **
    }
}
