import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise07 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. How many vehicles are Pink (ignore case)?
        int pinkCount = 0;
        for (Vehicle vehicle : vehicleMap.values()) {
            if ("pink".equalsIgnoreCase(vehicle.getColor())) { // Corrected comparison
                pinkCount++;
            }
        }
        System.out.println(pinkCount);
        // Expected: 54
    }
}
