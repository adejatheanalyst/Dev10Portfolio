import java.util.Random;

public class Exercise08 {

    // 1. Create a method.
    // Name: sumArray
    // Inputs: int[]
    // Output: int
    // Description: calculates the sum of the parameter's elements and returns it.
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
}





    public static void main(String[] args) {
        int[] values = makeRandomArray();
        // 2. Uncomment the code below and make it work.

        int sum = sumArray(values);
        System.out.println(sum);
        System.out.println(sumArray(makeRandomArray()));
        System.out.println(sumArray(makeRandomArray()));

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

