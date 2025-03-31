import java.util.Arrays;
import java.util.Random;

public class Exercise10 {

    public static void main(String[] args) {
        String[] bugs = makeBugArray();

        // The bugs array elements are either the value "beetle" or "mosquito".
        // 1. Count the number of beetles and mosquitoes.
        int beetleCount = 0;
        int mosquitoCount = 0;
        for (int i = 0; i < bugs.length; i++){
            if ("beetle".equals(bugs[i])) {
                beetleCount++; // add beetles to count if in list
            } else if ("mosquito".equals(bugs[i])){
                mosquitoCount++; // add mosquitos to count if found
            }


        }
        // 2. Print the result.
        System.out.println("Number of Beetles: " + beetleCount);
        System.out.println("Number of Mosquito's: " + mosquitoCount);
        // Results will vary because of randomness.
    }

    public static String[] makeBugArray() {
        String[] bugs = new String[200];
        Arrays.fill(bugs, "beetle");
        Random random = new Random();
        for (int i = 0; i < random.nextInt(150); i++) {
            bugs[random.nextInt(bugs.length)] = "mosquito";
        }
        return bugs;
    }
}
