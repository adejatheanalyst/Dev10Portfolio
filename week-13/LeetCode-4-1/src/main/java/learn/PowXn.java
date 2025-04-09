package learn;

public class PowXn {

    public static double powXN(double x, int n) {
//        double result = 0;
//        if (x < 100.0 && x > -100.0) { // make sure x is not 0
//            if (n == 0) { // if n is 0, return 1
//                result = 1;
//            } else if (n < 0) { // if power is negative, return 1 divided by number and negative power
//                result = 1 / powXN(x, -n); // convert into positive reciprocal
//            } else { // if n is positive multiply x by its self by n times subtracting from n each time.
//                result = x * powXN(x, n - 1);
//            }
//
//        }
//        return result;
//
//    }
    double result = 1;

            if (n == 0) { // if n is 0, return 1
        result = 1;
    } else if (n < 0) { // if power is negative, return 1 divided by number and negative power
        n = -n;
        x = 1 / x;
        // convert into positive reciprocal
    }
            while( n != 0){
        if((n & 1) != 0){
            result *= x;
        }
        x *= x;
        n >>>=1;
    }
           return result;
}








public static void main(String[] args) {
        double x = 2;
        int n = -2000000000;
        System.out.println(powXN(x, n));

    }
}
