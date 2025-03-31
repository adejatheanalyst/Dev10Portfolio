import learn.Color;

import java.util.HashSet;

public class Exercise11 {

    public static void main(String[] args) {
        HashSet<Color> colors = new HashSet<>();

        colors.add(new Color("Red"));
        colors.add(new Color("Red"));
        colors.add(new Color("Blue"));
        colors.add(new Color("Red"));
        colors.add(new Color("Yellow"));
        colors.add(new Color("Red"));
        colors.add(new Color("Blue"));
        colors.add(new Color("Blue"));
        colors.add(new Color("Red"));
        colors.add(new Color("Yellow"));
        colors.add(new Color("Yellow"));
        colors.add(new Color("Blue"));
        colors.add(new Color("Red"));

        // A HashSet is designed to prevent duplicate values.
        // Currently, each new Color is treated as a distinct value because class equality is the default reference
        // equality.
        // 1. Override the .equals and .hashCode in Color to compare values, not references.
        // 2. Confirm colors.size() is 3, not 13, below.

        System.out.println(colors.size());//True
    }
}
