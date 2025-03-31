public enum Color {
    RED("ff0000", "Rojo"),
    BLUE("0000ff", "Azul"),
    YELLOW("ffff00", "Amarillo"),
    GREEN("00ff00", "Verde"),
    ORANGE("ffa500", "Naranja"),
    PURPLE("a020f0", "Morado")
    ;
    private final String hexValue;
    private final String spanishName;

    Color(String hexValue, String spanishName) {
        this.hexValue = hexValue;
        this.spanishName = spanishName;
    }

    public String getHexValue() {
        return hexValue;
    }

    public String getSpanishName() {
        return spanishName;
    }
    // Returns the traditional compliment of this Color.
    public Color getTraditionalCompliment() {
        switch (this) {
            case RED:
                return GREEN;
            case BLUE:
                return ORANGE;
            case YELLOW:
                return PURPLE;
            case GREEN:
                return RED;
            case ORANGE:
                return BLUE;
            case PURPLE:
                return YELLOW;
        }
        return RED; // Required -- the compiler is paranoid.
    }

    // Find a Color by its hex value.
    public static Color findByHex(String hexValue) {
        for (Color color : Color.values()) {
            if (color.getHexValue().equalsIgnoreCase(hexValue)) {
                return color;
            }
        }
        String message = String.format("No Color with hex value: %s.", hexValue);
        throw new RuntimeException(message);
    }





}
