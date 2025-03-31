public class Exercise06 {

    public static void main(String[] args) {
        int[] values = {18, 42, 54, 93, 22};

        // 1. Create a loop to calculate the sum of elements in `values`.
     // declare sum for values to collect
        int sum = 0;
        for (int i = 0; i < values.length; i++){
            sum = sum + values[i];
        }

        // 2. Print the result.
        System.out.println(sum);


    }
}
