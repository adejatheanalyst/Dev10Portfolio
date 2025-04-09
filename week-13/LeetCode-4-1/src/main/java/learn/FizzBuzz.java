package learn;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {

        List<String> answer = new ArrayList<>();


//        for (int i = 1; i <= n; i++) {
//            if (i % 3 == 0 && i % 5 == 0) {
//                System.out.println("FizzBuzz");
//            } else if (i % 3 == 0) {
//                System.out.println("Fizz");
//            } else if (i % 5 == 0) {
//                System.out.println("Buzz");
//            } else {
//                System.out.println(i);
//            }
//        }

        for (int i = 1; i <= n; i++) {
            boolean divisibleby3 = i % 3 == 0;
            boolean divisibleby5 = i % 5 == 0;
            if (divisibleby3 && divisibleby5) {
                answer.add("FizzBuzz");
            } else if (divisibleby3) {
                answer.add("Fizz");
            } else if (divisibleby5) {
                answer.add("Buzz");
            } else {
                answer.add(String.valueOf(i));
            }
        }
        return answer;



    }


    public static void main(String[] args) {
        int n = 15;
        fizzBuzz(n);

    }
}
