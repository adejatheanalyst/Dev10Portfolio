public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 6);
        Musician brown = new Musician("James Brown", 8);
        Musician khan = new Musician("Chaka Khan", 10);
        System.out.println(ocean.toLine());
        // 2. Uncomment the line below and insure that it compiles and runs.
        System.out.println(ocean.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        // 4. Print each musicians' name and rating on a single line.
        System.out.println(brown.toLine());
        System.out.println(khan.toLine());
    }
}
