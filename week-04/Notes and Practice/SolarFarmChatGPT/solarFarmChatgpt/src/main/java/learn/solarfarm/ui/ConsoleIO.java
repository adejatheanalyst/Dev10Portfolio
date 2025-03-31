package learn.solarfarm.ui;
import java.util.Scanner;
public class ConsoleIO implements TextIO {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    public boolean readBoolean(String prompt) {
        System.out.print(prompt);
        return Boolean.parseBoolean(scanner.nextLine());
    }
}
