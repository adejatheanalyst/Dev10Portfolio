package learn.solarfarm.models;

/**
 * Represents a solar panel material.
 */
public enum Material {
    POLY_SI("Multicrystalline Silicon"),
    MONO_SI("Monocrystalline Silicon"),
    A_SI("Amorphous Silicon"),
    CD_TE("Cadmium Telluride"),
    CIGS("Copper Indium Gallium Selenide");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    /**
     * The material name.
     *
     * @return A String representing the material name.
     */
    public String getName() {
        return name;
    }
}
