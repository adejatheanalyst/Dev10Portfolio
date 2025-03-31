package org.example.ui;

import java.util.Scanner;

public class ConsoleIO implements TextIO {

    private Scanner console = new Scanner(System.in);

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }
}
