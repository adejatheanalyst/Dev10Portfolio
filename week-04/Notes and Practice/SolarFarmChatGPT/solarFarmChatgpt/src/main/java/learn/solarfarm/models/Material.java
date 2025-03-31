package learn.solarfarm.models;

public enum Material {
    MONO_SI("Monocrystalline Silicon"),
    POLY_SI("Polycrystalline Silicon"),
    THIN_FILM("Thin Film"),
    AMORPHOUS("Amorphous Silicon"),
    PERC("Passivated Emitter and Rear Cell");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
