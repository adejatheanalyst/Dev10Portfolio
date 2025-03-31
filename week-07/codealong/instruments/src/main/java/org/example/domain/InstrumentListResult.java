package org.example.domain;

import org.example.models.Instrument;

import java.util.ArrayList;
import java.util.List;

public class InstrumentListResult {

    private ArrayList<String> messages = new ArrayList<>();
    private List<Instrument> instrumentList;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public List<Instrument> getInstrumentList() {
        return instrumentList;
    }

    public void setInstrumentList(List<Instrument> instrumentList) {
        this.instrumentList = instrumentList;
    }
}

