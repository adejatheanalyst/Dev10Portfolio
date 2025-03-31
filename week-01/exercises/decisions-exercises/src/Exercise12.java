import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        // ARE ORDERED
        // Determine if three numbers are in order.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter the first value: ");
        int first = Integer.parseInt(console.nextLine());

        System.out.print("Enter the second value: ");
        int second = Integer.parseInt(console.nextLine());

        System.out.print("Enter the third value: ");
        int third = Integer.parseInt(console.nextLine());

        // 1. Add decisions statements to determine if first, second, and third are in order.
String orderStatus = "unordered";
if (first <= second && second <= third){
    orderStatus = "ordered";
}

        // 2. Print messages for both ordered and unordered cases.
        switch (orderStatus) {
            case "ordered":
                System.out.println("The numbers are in order.");
                break;
            case "unordered":
                System.out.println("The numbers are not in order.");
                break;
            default:
                System.out.println("Unexpected status.");
                break;}
    }
}
