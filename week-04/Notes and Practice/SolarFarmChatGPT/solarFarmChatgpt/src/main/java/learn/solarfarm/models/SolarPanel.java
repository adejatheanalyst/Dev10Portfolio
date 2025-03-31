package learn.solarfarm.models;
import learn.solarfarm.models.Material;
import  java.util.Objects;
public class SolarPanel {
    private int id;
    private String section;
    private int row;
    private int column;
    private int yearInstalled;
    private Material material;
    private boolean tracking;

    public SolarPanel(int id, String section, int row, int column, int yearInstalled, Material material, boolean tracking) {
        this.id = id;
        this.section = section;
        this.row = row;
        this.column = column;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.tracking = tracking;
    }

    // Getters and setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getColumn() { return column; }
    public void setColumn(int column) { this.column = column; }

    public int getYearInstalled() { return yearInstalled; }
    public void setYearInstalled(int yearInstalled) { this.yearInstalled = yearInstalled; }

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    public boolean isTracking() { return tracking; }
    public void setTracking(boolean tracking) { this.tracking = tracking; }

    public String toCSV() {
        return String.join(",", String.valueOf(id), section, String.valueOf(row), String.valueOf(column),
                String.valueOf(yearInstalled), material.name(), String.valueOf(tracking));
    }
    public String getKey() {
        return section + "-" + row + "-" + column;
    }
//    public enum Material {
//        MONO_SI, POLY_SI, AMORPHOUS, CADMIUM_TELLURIDE, COPPER_INDIUM
//    }

    @Override
    public String toString() {
        return String.format("ID: %d | Section: %s | Row: %d | Column: %d | Year: %d | Material: %s | Tracking: %s",
                id, section, row, column, yearInstalled, material.name(), tracking ? "Yes" : "No");
    }
}

