public class Exercise02 {

    public static void main(String[] args) {
        double result = multiplyThree(2, 2, 2);

        System.out.println(result); // Expected: 8.0
        System.out.println(multiplyThree(-1, 1, -1)); // Expected: 1.0
        System.out.println(multiplyThree(12, 0.5, 0.5)); // Expected: 3.0
    }

    // multiplyThree accepts threes doubles and multiplies them together.
    // 1. Finish implementing the multiplyThree method.
    public static double multiplyThree(double a, double b, double c) {
        return a * b * c ;
    }
}
