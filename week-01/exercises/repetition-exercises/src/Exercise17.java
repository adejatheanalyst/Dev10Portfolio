import java.util.Scanner;

public class Exercise17 {

    public static void main(String[] args) {
        // USER-DEFINED BOX
        // 1. Collect the following from a user: rows, columns, box character, border character.
        // 2. Use nested loops to print a user-defined box in the console.
        // (See Exercise16.)
        Scanner console = new Scanner(System.in);
        // collect rows number
        System.out.print("Enter Row number ");
        int rows = Integer.parseInt(console.nextLine());
        // collecting columns number
        System.out.print("Enter Column number ");
        int columns = Integer.parseInt(console.nextLine());
        // collect box character
        System.out.print("Enter a character ");
        char box = console.nextLine().charAt(0);
        //collect border character
        System.out.print("Enter another Character ");
        char border = console.nextLine().charAt(0);

        // loop for rows
        for (int r = 0; r < rows; r++) {

            //loop for columns
            for (int c = 0; c < columns; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == columns - 1) {
                    System.out.print(border);
                } else {
                    System.out.print(box);
                }
            }
            System.out.println();
        }

    }

}
