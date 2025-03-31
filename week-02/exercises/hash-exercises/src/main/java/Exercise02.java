import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise02 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Retrieve the vehicle with the VIN: 1M8GDM9AXKP042788 from `vehicleMap`. Store the vehicle in a variable.
        Vehicle car = vehicleMap.get("1M8GDM9AXKP042788");
        // 2. Print it to stdout. Confirm it's `null`. (The key does not exist in the map.)
        System.out.println(car);
    }
}
