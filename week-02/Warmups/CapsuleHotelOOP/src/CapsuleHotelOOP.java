import java.util.Scanner;

public class CapsuleHotelOOP {
    public static void main(String[] args) {
Scanner console = new Scanner(System.in);
 // initiating
Capsule one = new Capsule();
        one.checkIn("steve");
        one.checkOut();
        one.checkIn("steve");

//test check in process
//        System.out.print("Enter a guest name to check in: ");
//        String guestName = console.nextLine();
//        one.checkIn(guestName);
        // test is occupied
        System.out.println("Is the capsule occupied? " + one.isOccupied());

        //test is vacant
        System.out.println("Is the capsule empty? " + one.isVacant());

        one.checkOut();
        System.out.println("Is the capsule occupied? " + one.isOccupied());

        //test is vacant
        System.out.println("Is the capsule empty? " + one.isVacant());


    }
}