
import java.util.Scanner;

public class CapsuleHotel {
    public static void main(String[] args) {
// Hotel start up enter number of room
        System.out.println("=".repeat(51));
        System.out.println("Welcome to the Capsule Hotel!");
        Scanner console = new Scanner(System.in);
        System.out.println("=".repeat(51));

        int capacity = 0;

        while (true) {
            System.out.print("Enter the number of capsules to be available: ");
            String input = console.nextLine();

            // make sure input is valid and a positive number
            if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
                capacity = Integer.parseInt(input);
                System.out.println("There are " + capacity + " unoccupied capsules ready to be booked.");
                break;
            } else {
                System.out.println("Invalid input. Please enter a Number.");

            }
        }


        String[] guests = new String[capacity];
        int input = 0;

        do { // main Menu loop
            System.out.println("=".repeat(51));
            System.out.println("Guest Menu");
            System.out.println("=".repeat(51));
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. View Guests");
            System.out.println("4. Exit");
            System.out.println("-".repeat(51));
            System.out.print("Select [1-4]: ");

            try {
                input = Integer.parseInt(console.nextLine());
                switch (input) {
                    case 1 -> checkIn(console, guests, capacity);
                    case 2 -> checkOut(console, guests, capacity);
                    case 3 -> viewGuests(console, guests, capacity);
                    case 4 -> {
                        if (confirmExit(console)) {
                            System.out.println("Goodbye!");
                        } else {
                            input = -1;
                        }
                    }
                    default -> System.out.println("I don't understand that option. Please enter a number: [1-4] ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:[1-4] ");
            }
        } while (!(input == 4));
    }
    //check in a guest
    static void checkIn(Scanner console, String[] guests, int capacity) {
        // check if all rooms are filled
        boolean allFilled = true;
        for (String guest : guests){
            if (guest == null){
                allFilled = false;
                break;
            }
        }
        System.out.print("Enter guest name: ");
        String guestName = console.nextLine();
        System.out.print("Capsule #[1-"+ capacity + "]: ");
        int capsuleNumber = Integer.parseInt(console.nextLine());

        if (capsuleNumber < 1 || capsuleNumber > capacity) {
            System.out.println("Please enter a valid number!");
        } else if (guests[capsuleNumber - 1] != null) {
            System.out.println("Sorry, Capsule #" + capsuleNumber + " is already occupied!");
        } else {
            guests[capsuleNumber - 1] = guestName;
                System.out.println(guestName + " is booked in capsule " + capsuleNumber + ".");
            }

        }

    //check out a guest
    static void checkOut(Scanner console, String[] guests, int capacity) {
        System.out.print("Capsule #[1-"+ capacity + "]: ");
        int capsuleNumber = Integer.parseInt(console.nextLine());

        if (capsuleNumber < 1 || capsuleNumber > capacity) {
            System.out.print("Enter a valid capsule number!");
        } else if (guests[capsuleNumber - 1] == null) {
            System.out.println(" Sorry,\nCapsule #" + capsuleNumber + " is already unoccupied.");
        } else {
            System.out.println(guests[capsuleNumber - 1] + " checked out of # " + capsuleNumber + ".");
            guests[capsuleNumber - 1] = null;
        }
    }
    //view all guests
    static void viewGuests(Scanner console, String[] guests, int capacity) {
        System.out.println("=".repeat(51));
        System.out.println("View Guests");
        System.out.println("-".repeat(51));
        // prompt user to enter capsule number
        System.out.print("Capsule #[1-"+ capacity + "]: ");
        int capsuleNumber = Integer.parseInt(console.nextLine());

        if (capsuleNumber < 1 || capsuleNumber > capacity) {
            System.out.print("Enter a valid number.");
            return;
        }// kept getting out of bounds error//
        int start = Math.max(0, capsuleNumber - 6);
        int end = Math.min(guests.length, capsuleNumber + 5);// makes sure value does not exceed array length

        for (int i = start; i < end; i++) {
            System.out.printf("#%d: %s%n", i + 1, guests[i] == null ? "[empty]" : guests[i]);
        }
    }
    static boolean confirmExit(Scanner console){
        System.out.print("Are you sure you want to exit?\nAll information will be lost.\nExit?(y/n): ");
        String response = console.nextLine().trim().toLowerCase();
        return response.equals("y");
    }
}












