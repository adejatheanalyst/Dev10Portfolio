import java.util.Random;

public class Exercise11 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // 1. Count the number of positive elements in `values`.
        int posCount = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                posCount++; // add positive numbers to count
            }
        }
//        System.out.println(posNum);
        // 2. Create a new int[] to hold the positive elements.
        int[] positiveElements = new int[posCount];
        // (We must count first to know the capacity to allocate.)
        // 3. Loop through `values` a second time. Add positive elements to the new array.
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                positiveElements[index] = values[i];
                index++; // add positive numbers to count
            }
        }
//         4. Confirm the positive array is properly populated by printing its elements.
        System.out.println("Positive Numbers:" + posCount);
        for (int i = 0; i < positiveElements.length; i++) {
            System.out.println(positiveElements[i]);
        }

    }

        public static int[] makeRandomArray () {
            Random random = new Random();
            int[] result = new int[random.nextInt(100) + 50];
            for (int i = 0; i < result.length; i++) {
                result[i] = random.nextInt(1000) - 500;
            }
            return result;
        }
    }

