public class Exercise08 {

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.
    public static String getRandomFruit() {
//        int index = ((int) (Math.random() * 10));// another option you can do
        return switch ((int) (Math.random() * 10)) {
            case 0 -> "Apple";
            case 1 -> "Kiwi";
            case 2 -> "Orange";
            case 3 -> "Pineapple";
            case 4 -> "Grapes";
            case 5 -> "Tomato";
            case 6 -> "Mango";
            case 7 -> "Strawberry";
            case 8 -> "Plum";
            case 9 -> "HoneyDew";
            default -> "";
        };

    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
//        System.out.println(getRandomFruit());
        String result = getRandomFruit();
        System.out.println(result);
    }
}
