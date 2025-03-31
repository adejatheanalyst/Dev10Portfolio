package learn.foraging.ui;

public enum MainMenuOption {

    EXIT("Exit"),
    VIEW_FORAGES_BY_DATE("View Forages By Date"),
    VIEW_FORAGER("View Forager"),
    VIEW_ITEMS("View Items"),
    ADD_FORAGE("Add a Forage"),
    ADD_FORAGER("Add a Forager"),
    ADD_ITEM("Add an Item");

    private String message;

    private MainMenuOption(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
