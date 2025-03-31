package org.example.ui;

import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.util.List;

public class View {

    private TextIO io;

    public View(TextIO io) {
        this.io = io;
    }

    public void displayMessage(String message) {
        io.println(message);
    }

    public String readString(String prompt) {
        return io.readString(prompt);
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                String userString = readString(prompt);
                return Integer.parseInt(userString);
            } catch (NumberFormatException ex) {
                displayErrors(List.of("That was not an integer, try again"));
            }
        }
    }

    public String getMenuChoice() {
        displayMessage("Choose an option from the menu:");
        displayMessage("0. Exit");
        displayMessage("1. View all instruments");
        displayMessage("2. View instruments by type");
        displayMessage("3. Add an instrument");
        displayMessage("4. Update an instrument");
        displayMessage("5. Delete an instrument");
        return readString("Enter your selection: ");
    }

    public void printFormattedInstrument(Instrument instrument) {
        String studentString = instrument.getStudent().isEmpty() ? "Currently unused" : instrument.getStudent();
        String instrumentString = String.format(
                "%s - %s - %s - %s - %s",
                instrument.getSerialNumber(),
                instrument.getInstrumentType(),
                studentString,
                instrument.isNeedsRepair(),
                instrument.getCost()
        );
        displayMessage(instrumentString);
    }

    public void printFormattedInstruments(List<Instrument> instruments) {
        for (int i = 0; i < instruments.size(); i++) {
            int position = i + 1;
            io.print(position + ". "); // 1. asdasd
            printFormattedInstrument(instruments.get(i));
        }
    }

    public InstrumentType chooseInstrumentType() {
        InstrumentType chosenInstrumentType = null;
        while (chosenInstrumentType == null) {
            try {
                String instrumentTypeString = readString("Enter an instrument type: ");
                chosenInstrumentType = InstrumentType.valueOf(instrumentTypeString);
            } catch (IllegalArgumentException ex) {
                displayMessage("That isn't a valid instrument type, try again");
            }
        }
        return chosenInstrumentType;
    }

    public void displayErrors(List<String> errors) {
        displayMessage("Errors: ");
        for (String error : errors) {
            displayMessage(error);
        }
    }

    public Instrument addInstrument() {
        String serialNumber = readString("Enter the new instrument's serial number: ");
        InstrumentType instrumentType = chooseInstrumentType();
        String student = readString("Enter the student using the new instrument (leave blank if unused): ");
        int cost = readInt("Enter the new instrument's cost: ");
        boolean needsRepair = readBoolean("Does the new instrument need repairs?");

        return new Instrument(
                serialNumber,
                instrumentType,
                cost,
                student,
                needsRepair
        );
    }

    public boolean readBoolean(String prompt) {
        String userInput = readString(prompt + " [y/n]");
        return userInput.equalsIgnoreCase("y");
    }

    public Instrument chooseInstrumentFromList(List<Instrument> instruments) {
        int indexChoice = readInt("Select an instrument by its number: ");
        return instruments.get(indexChoice - 1);
    }

}