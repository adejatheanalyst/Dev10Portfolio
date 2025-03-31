import java.util.Scanner;

public class ChatGptCapsuleHotel {
    private String[] rooms;

    public ChatGptCapsuleHotel(int capacity) {
        rooms = new String[capacity];
    }

    public void checkIn(String guestName, int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms.length) {
            System.out.println("Error: Invalid room number.");
        } else if (rooms[roomNumber - 1] != null) {
            System.out.println("Error: Room is already occupied.");
        } else {
            rooms[roomNumber - 1] = guestName;
            System.out.println(guestName + " has been checked into room " + roomNumber + ".");
        }
    }

    public void checkOut(int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms.length) {
            System.out.println("Error: Invalid room number.");
        } else if (rooms[roomNumber - 1] == null) {
            System.out.println("Error: Room is already unoccupied.");
        } else {
            System.out.println(rooms[roomNumber - 1] + " has been checked out from room " + roomNumber + ".");
            rooms[roomNumber - 1] = null;
        }
    }

    public void viewGuests(int focusRoom) {
        if (focusRoom < 1 || focusRoom > rooms.length) {
            System.out.println("Error: Invalid room number.");
            return;
        }

        int start = Math.max(1, focusRoom - 5);
        int end = Math.min(rooms.length, focusRoom + 5);

        System.out.println("\nViewing rooms around room " + focusRoom + ":");
        for (int i = start; i <= end; i++) {
            String guest = rooms[i - 1] == null ? "[Empty]" : rooms[i - 1];
            System.out.println("Room " + i + ": " + guest);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the hotel capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        ChatGptCapsuleHotel hotel = new ChatGptCapsuleHotel(capacity);
        System.out.println("Hotel initialized with a capacity of " + capacity + " rooms.");

        while (true) {
            System.out.println("\n--- Capsule Hotel Menu ---");
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. View Guests");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number to check into: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    hotel.checkIn(guestName, roomNumber);
                    break;

                case 2:
                    System.out.print("Enter room number to check out: ");
                    roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    hotel.checkOut(roomNumber);
                    break;

                case 3:
                    System.out.print("Enter room number to focus on: ");
                    int focusRoom = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    hotel.viewGuests(focusRoom);
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Error: Invalid choice. Please try again.");
            }
        }
    }
}

