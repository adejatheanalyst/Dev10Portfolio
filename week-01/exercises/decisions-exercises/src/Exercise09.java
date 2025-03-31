import java.util.Scanner;
public class Exercise09 {
    public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
//        1. Add a class, `Exercise09`, to this project.
//        2. Add a `main` method.
//        3. Declare a `Scanner` variable.
//        4. Collect three pieces of data from the user: a minimum value, a maximum value, and an actual value.
//        5. Add `if/else` statements to determine if the actual value is between the min and max.
//        6. Print messages for both true and false cases.
        System.out.print("Enter a Minimum Value ");
        String input = console.nextLine();
        int minValue = Integer.parseInt(input);

        System.out.print("Enter a Maximum Value " );
        input = console.nextLine();
        int maxValue = Integer.parseInt(input);

        System.out.print("Enter an actual value ");
        input = console.nextLine();
        int actualValue = Integer.parseInt(input);

        if (actualValue >= minValue && actualValue <= maxValue){
            System.out.println("The actual value is between the minimum and maximum.");
        }else {
            System.out.println("The actual value is not between the minimum and maximum.");
        }
    }
}
