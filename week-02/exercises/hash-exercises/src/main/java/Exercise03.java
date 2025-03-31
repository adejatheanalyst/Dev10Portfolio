import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise03 {

    // 1. Create a method to print all Vehicles in a HashMap<String, Vehicle>.
    // Consider making it `public` so you can use it in other exercises.
public static void getAllVehicles(HashMap<String, Vehicle> vehicleMap){
    for(Vehicle vehicle : vehicleMap.values()){
        System.out.println(vehicle);
//        System.out.println(vehicle.getMake(), vehicle.getColor(), vehicle.getModel(), vehicle.getYear();
    }
}
    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 2. Print `vehicleMap` using your "print all" method.
        getAllVehicles(vehicleMap);



    }
}

