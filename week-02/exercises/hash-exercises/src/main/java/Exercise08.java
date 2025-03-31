import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise08 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Instantiate a new HashMap<String, Vehicle> named `twoThousandSix`.
        HashMap<String, Vehicle> twoThousandSix = new HashMap<>();
        // 2. Loop through `vehicleMap` and all 2006 vehicles to `twoThousandSix`;
        for (Vehicle vehicle : vehicleMap.values()) {
            if (vehicle.getYear() == 2006) { // Corrected comparison
                twoThousandSix.put(vehicle.getVin(),vehicle);
            }
        }
            // 3. Loop through `twoThousandSix` and display all vehicles.
        System.out.println("2006 vehicles");
            for (Vehicle vehicle : twoThousandSix.values()) {
                    System.out.println(vehicle);
                }
        System.out.println("\n Number of 2006 Vehicles: " + twoThousandSix.size());
            // (You may want to use your print all method from Exercise03.)
            // 4. How many 2006 vehicles are there? (Expected: 50)
        }
    }

