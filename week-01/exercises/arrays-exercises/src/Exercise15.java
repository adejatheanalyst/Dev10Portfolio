import java.util.Random;

public class Exercise15 {

    public static void main(String[] args) {
        int[] one = makeRandomArray();
        int[] two = makeRandomArray();

        // 1. Create a new int[] with room enough for all elements in `one` and `two`.
        int[] oneTwo = new int[one.length + two.length];

        // 2. Copy elements from `one` into the beginning of the array.
        for (int i = 0; i < one.length; i++) {
            oneTwo[i] = one[i];
        }
        // 3. Copy elements from `two` at the end of the array.
        for (int i = 0; i < two.length; i++) {
            oneTwo[one.length + i] = two[i];
        }

        // 4. Print the results to confirm that it worked.
            System.out.println("Combined Array:");
            for (int i = 0; i < oneTwo.length; i++) {
                System.out.print(oneTwo[i] + " ");

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
