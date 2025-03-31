package learn.solarfarm.ui;
import learn.solarfarm.models.Material;

import java.util.Scanner;
public class View {
    private final Scanner scanner = new Scanner(System.in);

    public View(TextIO io) {
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
        }
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void printError(String error) {
        System.err.println("Error: " + error);
    }


    public Material selectMaterial() {
        println("Choose a material:");
        Material[] materials = Material.values();
        for (int i = 0; i < materials.length; i++) {
            println((i + 1) + ". " + materials[i].name());
        }
        int choice;
        do {
            choice = readInt("Enter choice (1-" + materials.length + "): ");
            if (choice < 1 || choice > materials.length) {
                printError("Invalid choice. Please choose a number between 1 and " + materials.length + ".");
            }
        } while (choice < 1 || choice > materials.length);

        return materials[choice - 1];
    }
}
