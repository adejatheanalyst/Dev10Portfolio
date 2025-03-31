public class Exercise03 {

    public static void main(String[] args) {

        // 1. Add setters for both the name and rating fields in Musician.

        Musician one = new Musician("Frank Ocean", 10);
        System.out.println(one.getName());

        // 2. Use the appropriate setter to change Musician one's name to your favorite musician.
        one.setName("Beyonce");
        // (If Frank Ocean is your favorite musician, choose your second favorite.)

        System.out.println(one.getName());

        // Expected Output
        // Frank Ocean
        // [Your Favorite Musician]
    }
}
