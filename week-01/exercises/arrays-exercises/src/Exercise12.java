import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // 1. Count the number of positive and non-positive elements in `values`.
        int negCount = 0;
        int posCount = 0;
        for (int value : values) {// (int i = 0; i < values.length; i++)
            if (value > 0) {
                posCount++; // add positive numbers to count
            } else {
                negCount++;

            }
        }
//        for (int value : values){ // another way to view all index in an array
//            System.out.println(value);
//        }

//        System.out.println(posCount);
//        System.out.println(negCount);


        // 2. Create two new int[]s, one for positive elements and one for non-positive.
        int[] positiveElements = new int[posCount];
        int[] negativeElements = new int[negCount];
        // (We count first to determine the capacity to allocate.)
        // 3. Loop through `values` a second time. If an element is positive, add it to the positive array.
        // If it is non-positive, add it to the non-positive array.
        int posIndex = 0;
        int negIndex = 0;
        for (int value : values) {
            if (value > 0) {
                positiveElements[posIndex] = value;
                posIndex++; // add positive numbers to count
            } else  {
                negativeElements[negIndex] = value;
                negIndex++;
            }
        }// 4. Confirm that your secondary arrays are properly populated either by debugging or printing their elements.
        System.out.println("Positive Numbers:" + posCount + "\nNegative Numbers: "+ negCount);
        for (int i = 0; i < positiveElements.length; i++) {
            System.out.println(positiveElements[i]);
        }
        for (int i = 0; i < negativeElements.length; i++) {
            System.out.println(negativeElements[i]);
    }


    }


    public static int[] makeRandomArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }
}
