package learn.Data;

import learn.Model.Location;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static learn.TestData.*;
public class LocationRepositoryDouble implements LocationRepository {

ArrayList<Location> locations = new ArrayList<>();
public LocationRepositoryDouble(){
    locations.add(LOCATION);
    locations.add(LOCATION2);
    locations.add(LOCATION3);
}
    @Override
    public Location findById(int location_id) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getLocation_id() == location_id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Location> findByState(int state_id) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getState_id() == state_id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> findAll() throws DataAccessException {
        return locations;
    }

    @Override
    public List<Location> findByCity(String city) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getCity() == city)
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> findByUserId(User user_id) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getUser_id() == user_id)
                .collect(Collectors.toList());
    }

    @Override
    public Location findStandardRate(int location_id) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getLocation_id() == location_id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Location findWeekendRate(int location_id) throws DataAccessException {
        return locations.stream()
                .filter(i -> i.getLocation_id() == location_id)
                .findFirst()
                .orElse(null);
    }


    }



