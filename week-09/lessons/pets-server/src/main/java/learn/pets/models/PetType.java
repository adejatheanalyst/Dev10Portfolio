package learn.pets.models;

public enum PetType {
    DOG("Dog"), CAT("Cat"), REPTILE("Reptile"), RODENT("Rodent"), BIRD("Bird"), OTHER("Other");

    private final String displayText;

    PetType(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public static PetType findByDisplayText(String displayText) {
        for (PetType pt : values()) {
            if (pt.getDisplayText().equalsIgnoreCase(displayText)) {
                return pt;
            }
        }
        return null;
    }
}
