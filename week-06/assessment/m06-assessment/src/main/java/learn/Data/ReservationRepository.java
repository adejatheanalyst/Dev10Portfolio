package learn.Data;

import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {
    Reservation findById(int id) throws DataAccessException;
    List<Reservation> findByLocationId(Location location_id) throws DataAccessException;
    List<Reservation> findByUserId(User user_id) throws DataAccessException;
    List<Reservation> findAll() throws DataAccessException;
    List<Reservation> findByDate(LocalDate start_date, LocalDate end_date) throws DataAccessException;
    Reservation add(Reservation reservation) throws DataAccessException;
    boolean update(Reservation reservation) throws DataAccessException;
    boolean delete(Reservation reservation) throws DataAccessException;
    List<Reservation> findByEmail(String email) throws DataAccessException;


}
