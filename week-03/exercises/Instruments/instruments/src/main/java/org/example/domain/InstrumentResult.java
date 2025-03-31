package org.example.domain;

import org.example.models.Instrument;

import java.util.ArrayList;
import java.util.List;

public class InstrumentResult {

    private ArrayList<String> messages = new ArrayList<>();
    private Instrument instrument;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}