package learn.safari.models;

import java.time.LocalDate;

public class BugSighting {

    private int sightingId;
    private String bugType;
    private String description;
    private LocalDate date;
    private double interest;

    public BugSighting() {
    }

    public BugSighting(int sightingId, String bugType, String description, LocalDate date, double interest) {
        this.sightingId = sightingId;
        this.bugType = bugType;
        this.description = description;
        this.date = date;
        this.interest = interest;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void normalize() {
        if (bugType != null) {
            bugType = bugType.trim();
        }
        if (description != null) {
            description = description.trim();
        }
    }
}
