import java.util.HashSet;
import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> numbers = new HashSet<>();

        // 1. Generate 10 unique random numbers between 0 and 100.
        while(numbers.size()< 10){
            int randomNumber = random.nextInt(101);
            numbers.add(randomNumber);
        }
        // 2. Print the numbers to the console.
        System.out.println("size: " + numbers.size());
        // (Hint: You can add random numbers to the `numbers` HashSet until its size is 10.
        // That guarantees the numbers are unique.)
        for (int n : numbers) {
            System.out.println(n);
        }
    }
}