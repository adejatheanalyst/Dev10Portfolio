package learn.models;

public enum MoodType {
    HAPPY("happy"),
    SAD("sad"),
    ANGRY("angry"),
    ANXIOUS("anxious"),;

    private final String name;

    MoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}