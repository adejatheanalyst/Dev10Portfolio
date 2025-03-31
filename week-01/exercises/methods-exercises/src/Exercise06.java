public class Exercise06 {

    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.
    public static boolean isBetween( int num1, int num2, int num3 ){
        return num1 > num2 && num1 < num3; //

    }


    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(isBetween(5,1,10));
        System.out.println(isBetween(10,50,100));
        System.out.println(isBetween(4,3,5));
    }
}
