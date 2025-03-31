public class Exercise05 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's continents.
        String [] continents = new String[7];
        // Do not use array literal notation. Allocate space for 6 continents and then set each value by index.
        continents[0] = "Africa";
        continents[1] = "Antarctica";
        continents[2] = "Asia";
        continents[3] = "Australia";
        continents[4] = "Europe";
        continents[5] = "North America";
        continents[6] = "South America";

            // 2. Loop over each element and print it.
            for (int index = 0; index < continents.length; index++){
                String names = continents[index];
                System.out.println(names);
            }





    }
}
