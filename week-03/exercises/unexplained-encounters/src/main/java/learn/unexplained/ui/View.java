package learn.unexplained.ui;

import learn.unexplained.domain.EncounterResult;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner console = new Scanner(System.in);

    public MenuOption displayMenuGetOption() {
        printHeader("Main Menu");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s. %s%n", i + 1, options[i].getMessage());
        }

        String msg = String.format("Select [%s-%s]:", 1, options.length);
        int value = readInt(msg, 1, options.length);
        return options[value - 1];
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public void printAllEncounters(List<Encounter> encounters) {
        printHeader(MenuOption.DISPLAY_ALL.getMessage());
        if (encounters == null || encounters.size() == 0) {
            System.out.println();
            System.out.println("No encounters found.");
        } else {
            for (Encounter e : encounters) {
                System.out.printf("%s. Type:%s, Occurrences:%s, When:%s, Desc:%s%n",
                        e.getEncounterId(),
                        e.getType(),
                        e.getOccurrences(),
                        e.getWhen(),
                        e.getDescription());
            }
        }
    }

    public void printResult(EncounterResult result) {
        if (result.isSuccess()) {
            if (result.getPayload() == 0) {
                System.out.printf("Encounter Id %s added.%n", result.getPayload());
            }
        } else {
            printHeader("Errors");
            for (String msg : result.getMessages()) {
                System.out.printf("- %s%n", msg);
            }
        }
    }

    public Encounter makeEncounter() {
        printHeader(MenuOption.ADD.getMessage());
        Encounter encounter = new Encounter();
        encounter.setType(readType());
        encounter.setOccurrences(readInt("Number of occurrences:"));
        encounter.setWhen(readRequiredString("When:"));
        encounter.setDescription(readRequiredString("Description:"));
        return encounter;
    }

    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    private EncounterType readType() {
        int index = 1;
        for (EncounterType type : EncounterType.values()) {
            System.out.printf("%s. %s%n", index++, type);
        }
        index--;
        String msg = String.format("Select Encounter Type [1-%s]:", index);
        return EncounterType.values()[readInt(msg, 1, index) - 1];
    }
}
