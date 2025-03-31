package learn.Data;

import learn.Model.Location;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface LocationRepository {
//  static Location findById(int location_id) throws DataAccessException;

  Location findById(int location_id) throws DataAccessException;
  List<Location> findByState (int state) throws DataAccessException;
  List<Location> findAll() throws DataAccessException;
  List<Location> findByCity(String city) throws DataAccessException;
  List<Location> findByUserId(User user_id) throws DataAccessException;
  Location findStandardRate(int location_id) throws DataAccessException;
  Location findWeekendRate(int location_id) throws DataAccessException;
}
