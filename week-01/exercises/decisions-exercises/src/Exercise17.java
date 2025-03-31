import java.util.Scanner;

public class Exercise17 {

    public static void main(String[] args) {
        // SWITCH HOMEWORK
        Scanner console = new Scanner(System.in);

        System.out.print("Hours of homework: ");
        int hoursOfHomework = Integer.parseInt(console.nextLine());

        System.out.println("Day of the week [1-7]: ");
        int dayOfWeek = Integer.parseInt(console.nextLine());
boolean doHomework = false; // default to not do homework
        // 1. Re-implement Exercise07 using a switch statement.
        switch (dayOfWeek){
            case 6,7: //Saturday
                if (hoursOfHomework < 15) {
                    doHomework = false;
                    System.out.println("Abdi will skip his homework.");
                } else {
                    doHomework = true;
                    System.out.println("Abdi will do his homework.");}
                break;
            default: // any other weekday (1-5)
                if (hoursOfHomework > 15) {
                    doHomework = true;
                } else {
                    doHomework = false;
                }
            if (doHomework) {
                    System.out.println("Abdi will do his homework.");
                } else {
                    System.out.println("Abdi will skip his homework.");
                }

        }
        // Days 6 and 7 represent Saturday and Sunday.
        // If it's the weekend and Abdi has less than 15 hours of homework, he skips homework for the day.
        // For all other days, or if he has more than 15 hours of homework, he does his homework.

//if (hoursOfHomework > 15){
//    System.out.println("Have to do hw");
//} else {
//    switch (dayOfWeek){
//        case 6, 7:
//            System.out.println("No HW WOOHOO!");
//            break;
//        case 1,2,3,4,5:
//            System.out.println("have to do hw");
//            break;
//        default:
//            System.out.println("Please enter valid number");
    }
}
        // ---
        // You may choose to track data -- maybe a boolean for homework yes/no -- instead of printing a message in
        // each case. That's a lot of repeated typing.
        // Then print the detailed message after the switch.


