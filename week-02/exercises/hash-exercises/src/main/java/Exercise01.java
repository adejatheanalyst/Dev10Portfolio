import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise01 {

    public static void main(String[] args) {

        // `vehicleMap` is a HashMap that holds 1000 vehicles.
        // The key is the VIN (https://en.wikipedia.org/wiki/Vehicle_identification_number) as a String.
        // The value is an instance of a Vehicle.

        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Retrieve the vehicle with the VIN: 2G4WD582061270646 from `vehicleMap`. Store the vehicle in a variable.
        Vehicle car = vehicleMap.get("2G4WD582061270646");
        // 2. Print it to stdout. Confirm it's a Khaki 1989 Buick LeSabre.
        System.out.println(car);
    }
}
