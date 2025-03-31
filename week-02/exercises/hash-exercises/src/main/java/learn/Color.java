package learn;

import java.util.Objects;

public class Color {

    private final String name;

    public Color(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(name, color.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
// 1. Override Color .equals and .hashCode to use the `name` field.
    // (Hint: IntelliJ can generate these methods for you.)
}
