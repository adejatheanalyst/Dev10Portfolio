package learn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class VehicleRepository {

    public static HashMap<String, Vehicle> getMap() {

        HashMap<String, Vehicle> result = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("./data/cars.csv"))) {

            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);

                if (fields.length == 5) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(fields[0]);
                    vehicle.setMake(fields[1]);
                    vehicle.setModel(fields[2]);
                    vehicle.setYear(Integer.parseInt(fields[3]));
                    vehicle.setColor(fields[4]);
                    result.put(vehicle.getVin(), vehicle);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
