import java.util.Arrays;

public class Hero {

    private String name;
    private Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Power[] getPowers() {
        return powers;
    }

    public void setPowers(Power[] powers) {
        this.powers = powers;
    }

// 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.
//    public String toLine(){
//        return String.format("Hero's Name: " + name + " Powers: " + Arrays.toString(powers));
//   }

    String toLine() {
        String output = name + ": ";

        String delimiter = "";
        for (Power p : getPowers()) {
            output += delimiter;
            delimiter = ",";
            output += p.getName();
        }
        return output;
    }
}
//        StringBuilder powersList = new StringBuilder();
//        for (int i = 0; i < powers.length; i++) {
//            powersList.append(powers[i].getName());
//            if (i < powers.length - 1) {
//                powersList.append(", "); // Add a comma between powers
//            }
//        }
//        return name + ": " + powersList.toString();



