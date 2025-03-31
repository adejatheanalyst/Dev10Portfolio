package org.example.models;

public class Instrument {

    // TODO
    // add an int instrumentId to hold the db id

    private String serialNumber;

    private InstrumentType instrumentType;

    private int cost;

    private String student;

    private boolean needsRepair;

    public Instrument(String serialNumber, InstrumentType instrumentType, int cost, String student, boolean needsRepair) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
        this.cost = cost;
        this.student = student;
        this.needsRepair = needsRepair;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public boolean isNeedsRepair() {
        return needsRepair;
    }

    public void setNeedsRepair(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }
}
