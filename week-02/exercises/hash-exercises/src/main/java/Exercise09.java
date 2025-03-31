import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise09 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Print the size of `vehicleMap`.
//        System.out.println(vehicleMap.size());
        // 2. Remove VIN 2G4WD582061270646
        vehicleMap.remove("2G4WD582061270646");
        // 3. Remove VIN 1M8GDM9AXKP042788
        vehicleMap.remove("1M8GDM9AXKP042788");
        // 4. Print the size of `vehicleMap`. (Expected: 999)
        System.out.println(vehicleMap.size());
    }
}
