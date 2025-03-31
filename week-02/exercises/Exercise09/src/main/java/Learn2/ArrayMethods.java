package Learn2;

public class ArrayMethods {
    public static int[] removeZero(int[] input) {
        // Step 1: Count the non-zero elements
        int count = 0;
        for (int value : input) {
            if (value != 0) {
                count++;
            }
        }
        // Create a new array with values that are not 0
        int[] result = new int[count];
        int index = 0;

        //copy those values to new array
        for (int value : input) {
            if (value != 0) {
                result[index++] = value;
            }
        }

        return result;
    }
}
