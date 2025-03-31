public class Exercise07 {

    public static void main(String[] args) {
        // 1. Calculate the number of offices in a 23 story building
        int buildingFloors = 23;
        int rows;
        int columns;
        
        // where each floor has 15 "rows" and 8 "columns" of offices.
        rows = 15;
        columns = 8;
        int officesPerFloor = rows * columns;
                
        // 2. Use whatever approach you think is best.
       int totalOffices = officesPerFloor * buildingFloors;
        // 3. Print the result.
        System.out.println("Total number of offices in 23 Story building is " + totalOffices);
    }
}
