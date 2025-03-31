package learn.UI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleIO {
    private static final String INVALID_EMAIL
            = "[INVALID] Enter a valid email.";
    private static final String INVALID_DATE
            = "[INVALID] Enter a date in MM/dd/yyyy format.";
    private static final String REQUIRED
            = "[INVALID] Value is required.";
    private static final String INVALID_NUMBER
            = "[INVALID] Enter a valid number.";

    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    public void print(String message) {
        System.out.print(message);
    }
    public void println() {
        System.out.println();
    }
    public void println(String message) {
        System.out.println(message);
    }
    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }
    public int readInt(String prompt, int i, int size) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                println(INVALID_NUMBER);
            }
        }
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine().trim();
    }

    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!result.isBlank()) {
                return result;
            }
            println(REQUIRED);
        }
    }

    public String readEmail(String prompt) {
        while (true) {
            String result = readRequiredString(prompt);
            if (result.contains("@") && result.contains(".")) {
                return result;
            }
            println(INVALID_EMAIL);
        }
    }
    public boolean readBoolean(String prompt) {
        while (true) {
            String result = readRequiredString(prompt);
            if (result.equalsIgnoreCase("y") || result.equalsIgnoreCase("yes")) {
                return true;
            }
            if (result.equalsIgnoreCase("n") || result.equalsIgnoreCase("no")) {
                return false;
            }
            println("[INVALID] Please enter 'y' or 'n'.");
        }
    }
    public LocalDate readLocalDate(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine().trim();
        if(input.isEmpty()) {
            return null;
        }
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception ex) {
                println(INVALID_DATE);
                return readLocalDate(prompt);
            }
        }

    public LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max) {
        while (true) {
            LocalDate result = readLocalDate(prompt);
            if (result.isAfter(min) && result.isBefore(max)) {
                return result;
            }
            println("[INVALID] Date must be between " + min.format(formatter) + " and " + max.format(formatter) + ".");
        }
    }
    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String input = readRequiredString(prompt);
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException ex) {
                println("[INVALID] Enter a valid number.");
            }
        }
    }


}
