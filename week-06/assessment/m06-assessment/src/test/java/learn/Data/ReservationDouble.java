package learn.Data;

import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static learn.TestData.*;

public class ReservationDouble implements ReservationRepository {
   ArrayList<Reservation> reservations = new ArrayList<>();
   public ReservationDouble() {
       reservations.add(RESERVATION);
       reservations.add(RESERVATION2);
   }
    @Override
    public Reservation findById(int id) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getReservation_id() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Reservation> findByLocationId(Location location_id) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getLocation() == location_id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByUserId(User user_id) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getUser() == user_id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findAll() throws DataAccessException {
        return reservations;
    }

    @Override
    public List<Reservation> findByDate(LocalDate start_date, LocalDate end_date) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getStart_date().equals(start_date) && i.getEnd_date().equals(end_date))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        reservations.add(reservation);
        return reservation;

    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
       for (Reservation r : findAll()){
              if (r.getReservation_id() == reservation.getReservation_id()){
                r.setStart_date(reservation.getStart_date());
                r.setEnd_date(reservation.getEnd_date());
                r.setTotal(reservation.getTotal());
                return true;
              }
       }
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataAccessException {
        return reservations.remove(reservation);
    }

    @Override
    public List<Reservation> findByEmail(String email) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getEmail().equals(email))
                .collect(Collectors.toList());
    }

}
