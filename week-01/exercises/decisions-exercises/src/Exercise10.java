import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // USPS
        // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
        // If a lbs/zone combo does not exist, print a warning message for the user.
// 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
        Scanner console = new Scanner(System.in);
        System.out.print("How much does your parcel weigh? ");
        int weight = Integer.parseInt(console.nextLine());
        System.out.print("Which zone are you in? [1, 2, or 3] ");
        String zone = console.nextLine();

        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        double price = 0.0;
        if (zone.equals("1") || zone.equals("2")) {
            if (weight == 1) price = 7.50;
            else if (weight == 2) price = 8.25;
            else if (weight == 3) price = 8.70;
            else if (weight == 4) price = 9.20;
            else if (weight == 5) price = 10.20;
        } else if (zone.equals("3")){
            if (weight == 1) price = 7.85;
            else if (weight == 2) price = 8.70;
            else if (weight == 3) price = 9.70;
            else if (weight == 4) price = 10.55;
            else if (weight == 5) price = 11.30;
        }
        // If a lbs/zone combo does not exist, print a warning message for the user.
        if (weight < 1 || weight > 5) {
            System.out.println("Weight must be between 1 and 5 lbs.");
        } else if (!zone.equals("1") && !zone.equals("2") && !zone.equals("3")) {
            System.out.println("Invalid zone. Please enter 1, 2, or 3.");
        }


        System.out.printf("The cost to ship your parcel is $%.2f.%n", price);
    }
}
