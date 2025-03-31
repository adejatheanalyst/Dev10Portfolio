public class Exercise05 {

    // 1. Read the isWithinFiveOfAHundred JavaDocs.
    // 2. Implement isWithinFiveOfAHundred.
    // 3. Create tests for isWithinFiveOfAHundred and confirm that it is correct.

    /**
     * Determines if a value is within 5 of any number evenly divisible by 100.
     * Examples
     * -105 to -95: true
     * -94 to -6: false
     * -5 to 5: true
     * 6 to 94: false
     * 95 to 105: true
     * 106 to 194: false
     * 195 to 205: true
     * 206 to 294: false
     * ...continues in both the positive and negative directions...
     *
     * @param value the number to test
     * @return true if value is within 5 of a number evenly divisible by 100, false if not.
     */
    public static boolean isWithinFiveOfAHundred(int value) {
        int remainder = value % 100;
        return (remainder >= -5 && remainder <=5) ||(remainder >= 95 || remainder <= -95);
    }
}
