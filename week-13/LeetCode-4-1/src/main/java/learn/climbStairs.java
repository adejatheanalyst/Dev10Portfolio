package learn;

public class climbStairs {


    public static int climbStairs(int n) {
        if(n< 0){
            throw new RuntimeException("Cannot be negative");
        }
        if(n == 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 3;
        }
        int previous = 1;
        int prePrevious = 0;
        int next = 0;
        for(int i = 2; i< n; i++){
            next = previous + prePrevious;
            prePrevious = previous;
            previous = next;
        }
        return previous;
    }


    public static void main(String[] args) {
        int n = 3;
        System.out.println(climbStairs(n));

    }
}
