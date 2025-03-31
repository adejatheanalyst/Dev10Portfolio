public class Exercise03 {

    public static void main(String[] args) {
        float result = milesToKilometers(12.0f);

        System.out.println(result); // Expected (roughly): 19.32
        System.out.println(milesToKilometers(1024f)); // Expected: 1648.64
        System.out.println(milesToKilometers(123.45f)); // Expected: 198.7545

        // 2. Add at least one more milesToKilometers call and confirm it works.
        System.out.println(milesToKilometers(50f));
    }

    // milesToKilometers converts miles to kilometers.
    // 1. Complete the milesToKilometers method.
    public static float milesToKilometers(float miles) {
        return miles * 1.61f;
    }
}
