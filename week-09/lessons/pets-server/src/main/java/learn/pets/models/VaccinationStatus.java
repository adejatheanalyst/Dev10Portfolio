package learn.pets.models;

public enum VaccinationStatus {
    UP_TO_DATE("Up to Date"), NOT_UP_TO_DATE("Not up to Date"), UNKNOWN("Unknown");

    private final String displayText;

    VaccinationStatus(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public static VaccinationStatus findByDisplayText(String displayText) {
        for (VaccinationStatus vs : values()) {
            if (vs.getDisplayText().equalsIgnoreCase(displayText)) {
                return vs;
            }
        }
        return null;
    }
}
