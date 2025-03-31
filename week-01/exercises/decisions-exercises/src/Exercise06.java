import java.util.Scanner;

public class Exercise06 {

    public static void main(String[] args) {
        // CRUISE SHIP ACTIVITIES
        // ======================
        // When the cruise ship docks at a port, passengers can choose from one of two activities: snorkeling and shopping.
        // Snorkelers must always have an even number since they use the buddy system.
        // Use if/else statements to apply the following rules:
        // If the number of snorkelers is even, all is well. Everyone gets their preferred activity.
        // If it's not, add the snorkeler count to the shopper count and set snorkelers to 0.
        // Print the total snorkelers and shoppers.

        Scanner console = new Scanner(System.in);
        System.out.print("# of Snorkelers: ");
        int snorkelerCount = Integer.parseInt(console.nextLine());

        System.out.print("# of Shoppers: ");
        int shopperCount = Integer.parseInt(console.nextLine());

        // 1. Apply if/else here.

        if (snorkelerCount % 2 == 0) {
            System.out.println("All set! Have Fun!");
        } else {
            snorkelerCount += shopperCount; // add snorkelers to shoppers
            snorkelerCount = 0; //Set Snorkelers to 0
        }
        System.out.println("Total Snorkelers: " + snorkelerCount);
        System.out.println("Total Shoppers: " + shopperCount);
    }
}
