package learn;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
    public int fibonacci(int n){
        if(n< 0){
            throw new RuntimeException("Cannot be negative");
        }
        if(n == 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
// constant space complexity
//        List<Integer> nums = new ArrayList<>();
//        nums.add(0);
//        nums.add(1);
        int previous = 1;
        int prePrevious = 0;
        int next = 0;
        for(int i = 2; i< n; i++){
             next = previous + prePrevious;
//            int previous = nums.getLast();
//            int prePrevious = nums.get(nums.size() - 2);
//            nums.add(previous + prePrevious);
            prePrevious = previous;
            previous = next;
        }
        return previous;
    }





    public static void main(String[] args) {
        int [] values = {0,1,1,2,3,5,8,13,21,34};
        int index = 5;
        for(int i = 1; i < 11; i++){
            System.out.println((new Fibonacci().fibonacci(i)));
        }



    }
}
