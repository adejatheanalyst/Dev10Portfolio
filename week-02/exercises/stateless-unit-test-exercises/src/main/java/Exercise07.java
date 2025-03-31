import java.util.Arrays;

public class Exercise07 {

    // 1. Read the reverse JavaDocs.
    // 2. Implement reverse.
    // 3. Create tests for reverse and confirm that it is correct.

    /**
     * Reverses the order of elements in an array argument and returns them in a new array.
     * It does not alter the existing array.
     *
     * @param values the array to reverse
     * @return a new array with elements in reverse order.
     */
    public static String[] reverse(String[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[values.length - 1 - i];
        }
            return result;
        }
    }
