package org.example.ui;

import org.example.data.DataAccessException;
import org.example.domain.InstrumentListResult;
import org.example.domain.InstrumentResult;
import org.example.domain.InstrumentService;
import org.example.domain.SerialNumberResult;
import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.util.List;

public class Controller {

    private View view;

    private InstrumentService service;

    private boolean stillRunning = true;

    public Controller(View view, InstrumentService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        try {
            runDangerously();
        } catch (DataAccessException ex) {
            view.displayErrors(List.of(ex.getMessage()));
        }
    }

    private void runDangerously() throws DataAccessException {
        view.displayMessage("Welcome to instrument inventory!");

        String userInput = null;
        while (stillRunning) {
            userInput = view.getMenuChoice();
            switch (userInput) {
                case "0" -> exit();
                case "1" -> viewAll();
                case "2" -> viewByType();
                case "3" -> add();
                case "4" -> update();
                case "5" -> delete();
                default -> handleDefaultMenuChoice();
            }
        }
    }

    private void exit() {
        view.displayMessage("Goodbye!");
        this.stillRunning = false;
    }

    private void viewAll() throws DataAccessException {
        displayAllInstruments();
    }

    private void viewByType() throws DataAccessException {
        InstrumentType chosenInstrumentType = view.chooseInstrumentType();
        InstrumentListResult filteredInstrumentsResult = service.findByType(chosenInstrumentType);
        if (filteredInstrumentsResult.isSuccess()) {
            view.printFormattedInstruments(filteredInstrumentsResult.getInstrumentList());
        } else {
            view.displayErrors(filteredInstrumentsResult.getErrorMessages());
        }
    }

    private void add() throws DataAccessException {
        Instrument instrumentToAdd = view.addInstrument();
        InstrumentResult result = service.add(instrumentToAdd);

        handleInstrumentResult(result, "Successfully added an instrument!");
    }

    private void update() throws DataAccessException {
        List<Instrument> allInstruments = displayAllInstruments();
        Instrument instrumentToEdit = view.chooseInstrumentFromList(allInstruments);

        String newStudent = view.readString("Enter the new student: ");
        int newCost = view.readInt("Enter the new cost: ");
        boolean newNeedsRepair = view.readBoolean("Does it need repairs?");

        instrumentToEdit.setStudent(newStudent);
        instrumentToEdit.setCost(newCost);
        instrumentToEdit.setNeedsRepair(newNeedsRepair);

        InstrumentResult result = service.update(instrumentToEdit);

        handleInstrumentResult(result, "Successfully edited an instrument!");
    }

    private void delete() throws DataAccessException {
        List<Instrument> allInstruments = displayAllInstruments();
        Instrument instrumentToDelete = view.chooseInstrumentFromList(allInstruments);

        SerialNumberResult result = service.deleteBySerialNumber(instrumentToDelete.getSerialNumber());

        if (result.isSuccess()) {
            view.displayMessage("Successfully deleted the instrument");
            view.printFormattedInstrument(instrumentToDelete);
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void handleDefaultMenuChoice() {
        view.displayMessage("I don't understand that option, try again");
    }

    private void handleInstrumentResult(InstrumentResult result, String successMessage) {
        if (result.isSuccess()) {
            view.displayMessage(successMessage);
            view.printFormattedInstrument(result.getInstrument());
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private List<Instrument> displayAllInstruments() throws DataAccessException {
        view.displayMessage("Here are the existing instruments:");
        List<Instrument> allInstruments = service.findAll();
        view.printFormattedInstruments(allInstruments);
        return allInstruments;
    }
}