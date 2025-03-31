package org.example.domain;

import org.example.models.Instrument;

import java.util.ArrayList;
import java.util.List;

public class SerialNumberResult {

    private ArrayList<String> messages = new ArrayList<>();
    private String serialNumber;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}