import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise05 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Instantiate a new HashMap<String, Vehicle>.
        HashMap<String, Vehicle> vehicleMap2 = new HashMap<>();
        // 2. Add two vehicles to the new map.
        Vehicle newVehicle = new Vehicle("000000223", "Chevrolet", "Malibu", 2016, "Yellow");
        Vehicle newVehicle2 = new Vehicle("1111111253", "Chevrolet", "Tonka", 2020, "Red");
        vehicleMap2.put(newVehicle.getVin(), newVehicle);
        vehicleMap2.put(newVehicle2.getVin(), newVehicle2);
        // 3. Add items from the new map to `vehicleMap` using the `putAll` method.
        vehicleMap.putAll(vehicleMap2);
        // 4. Confirm the vehicles were added by retrieving on with its VIN and printing it to the console.
        System.out.println(vehicleMap);
    }
}
