import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;
import java.util.Objects;

public class Exercise06 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Loop over each vehicle in `vehicleMap` and print vehicles with a Dodge make.
        System.out.println("Normal method");
        System.out.println("=".repeat(80));
        for (Vehicle vehicle : vehicleMap.values()) {
            if ("Dodge".equals(vehicle.getMake())) { // Corrected comparison
                System.out.println(vehicle);
            }
            // 2. Loop three times with three different techniques: .values(), .entrySet(), and .keySet().
        }
        System.out.println("Entry method");
        System.out.println("=".repeat(80));
        for (HashMap.Entry<String, Vehicle> entry : vehicleMap.entrySet()) {
            if ("Dodge".equals(entry.getValue().getMake())) {
                System.out.println(entry.getValue());
            }
        }
        System.out.println("Keyset method");
        System.out.println("=".repeat(80));
        for (String key : vehicleMap.keySet()) {
            Vehicle vehicle = vehicleMap.get(key);
            if ("Dodge".equals(vehicle.getMake())) {
                System.out.println(vehicle);
            }
        }
    }
}


