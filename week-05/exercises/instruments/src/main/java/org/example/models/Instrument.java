package org.example.models;

public class Instrument {
private int instrumentId;
    private String serialNumber;

    private InstrumentType instrumentType;

    private int cost;

    private String student;

    private boolean needsRepair;

    public Instrument( String serialNumber,InstrumentType instrumentType, int cost, String student, boolean needsRepair) {
        this.serialNumber = serialNumber;
        this.instrumentType = instrumentType;
        this.cost = cost;
        this.student = student;
        this.needsRepair = needsRepair;
    }

    public Instrument( int instrumentId) {
        this.instrumentId = instrumentId;

    }

    public Instrument() {

    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getSerialNumber() {
        return serialNumber;
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
