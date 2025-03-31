import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise04 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Create a new Vehicle. Use a VIN that's easy to remember.
        Vehicle newVehicle = new Vehicle("12345678E", "Chevrolet", "Corvette", 2016, "Blue");

        // 2. Add the Vehicle to `vehicleMap` with the `put` method.
        vehicleMap.put(newVehicle.getVin(), newVehicle);  // Using VIN as the key

        // 3. Confirm the Vehicle was added by retrieving it with `get` and printing it to the console.
        Vehicle retrievedVehicle = vehicleMap.get(newVehicle.getVin());
        System.out.println("Retrieved Vehicle: " + retrievedVehicle);
    }
}
