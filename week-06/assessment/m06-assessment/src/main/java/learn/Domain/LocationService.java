package learn.Domain;

import learn.Data.LocationRepository;
import learn.Model.Location;
import learn.Model.User;

import java.util.List;

public class LocationService {
    public final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }
    List<Location> findAll(){
        return repository.findAll();
    }

    LocationResult<Location> findById(int id){
        Location location = repository.findById(id);
        LocationResult<Location> result = new LocationResult<>();
        if (location == null) {
            LocationResult.addErrorMessage("User not found.");
        }else {
            result.setLocation(location);
        }
        return result;
    }
    LocationResult<Location> findStandardRate(int location_id) {
        Location result = repository.findStandardRate(location_id);
        LocationResult<Location> locationResult = new LocationResult<>();
        if (result == null) {
            LocationResult.addErrorMessage("No Rates found for that location.");
        } else {
            locationResult.setLocation(result);
        }return locationResult;
    }
    LocationResult<Location> findWeekendRate(int location_id) {
        Location result = repository.findWeekendRate(location_id);
        LocationResult<Location> locationResult = new LocationResult<>();
        if (result == null) {
            LocationResult.addErrorMessage("No Rates found for that location.");
        } else {
            locationResult.setLocation(result);
        }return locationResult;
    }


    LocationResult<Location> findByState(int state){
        List<Location> locations = repository.findByState(state);
        LocationResult<Location> result = new LocationResult<>();
        if (locations == null || locations.isEmpty()) {
            LocationResult.addErrorMessage("No locations found with that ID. ");
        } else {
            for (Location location : locations){
                LocationResult<Location> validateResult = validate(location);
                if(!validateResult.isSuccess()){
                    LocationResult.addErrorMessage("Invalid location");
                }
            }
            result.setLocationList(locations);
        }
        return result;
    }
    LocationResult<Location>  findByCity(String city){
        List<Location> locations = repository.findByCity(city);
        LocationResult<Location> result = new LocationResult<>();
        if (locations.isEmpty()) {
            LocationResult.addErrorMessage("No locations found in city " + city);
        } else {
            for (Location location : locations){
                LocationResult<Location> validateResult = validate(location);
                if(!validateResult.isSuccess()){
                    LocationResult.addErrorMessage("Invalid City");
                }
            }
            result.setLocationList(locations);
        }
        return result;
    }
    public LocationResult<Location> findByUserId(User user_id){
        List<Location> locations = repository.findByUserId(user_id);
        LocationResult<Location> result = new LocationResult<>();
        if (locations.isEmpty()) {
            result.addErrorMessage("No locations found for Host");
            return result;
        } else {
            for (Location location : locations){
                LocationResult<Location> validateResult = validate(location);
                if (!validateResult.isSuccess()){
                    result.addErrorMessage("Invalid Host Information");
                }
            } result.setLocationList(locations);
        }
        return result;
    }
    LocationResult<Location> validate(Location location) {
        LocationResult<Location> result = new LocationResult<>();
        if (location == null) {
            LocationResult.addErrorMessage("Location cannot be null");
            return result;
        }

        if(location.getAddress() == null || location.getAddress().isBlank()){
            LocationResult.addErrorMessage("Location address is required");
        }
        if(location.getStateId() == 0 || location.getStateId() < 0){
            LocationResult.addErrorMessage("Location state is required");
        }
        if(location.getCity() == null || location.getCity().isBlank()){
            LocationResult.addErrorMessage("Location city is required");
        }
        if(location.getPostal_code() == null || location.getPostal_code().isBlank()){
            LocationResult.addErrorMessage("Location zip is required");
        }
        return result;

    }

}
