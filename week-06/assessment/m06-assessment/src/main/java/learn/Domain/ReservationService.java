package learn.Domain;

import learn.Data.LocationRepository;
import learn.Data.ReservationRepository;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationService {

    private ReservationRepository repository;
    private LocationRepository locationRepository;

    public ReservationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ReservationService(LocationRepository locationRepository, ReservationRepository repository) {
        this.locationRepository = locationRepository;
        this.repository = repository;
    }
    public ReservationService(){}

    List<Reservation> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public ReservationResult<Reservation> findById(int id) throws DataAccessException {
        Reservation reservation = repository.findById(id);
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservation == null) {
            ReservationResult.addErrorMessage("Reservation not found");
        } else {
            result.setReservation(reservation);
            result.setSuccess(true);
        }
        return result;
    }

    public ReservationResult<Reservation> findByLocationId(Location location_id) throws DataAccessException {
        List<Reservation> reservations = repository.findByLocationId(location_id);
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservations.isEmpty()) {
            ReservationResult.addErrorMessage("No reservations found for this location");
        } else {
            result.setReservations(reservations);
            result.setSuccess(true);
        }
        return result;
    }


    public ReservationResult<Reservation> findByUser(User user_id) throws DataAccessException {
        List<Reservation> reservations = repository.findByUserId(user_id);
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservations.isEmpty()) {
            ReservationResult.addErrorMessage("No reservations found for this user");
        } else {
            result.setReservations(reservations);
            result.setSuccess(true);
        }return result;
    }

    public ReservationResult<Reservation> findByDate(LocalDate start_date, LocalDate end_date) throws DataAccessException {
        List<Reservation> reservations = repository.findByDate(start_date, end_date);
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservations.isEmpty()) {
            ReservationResult.addErrorMessage("No reservations found for this date range");
        }else {
            result.setReservations(reservations);
            result.setSuccess(true);
        }return result;
    }

    public ReservationResult<Reservation> findByEmail(String email) {
        List<Reservation> reservations = repository.findByEmail(email);
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservations.isEmpty()) {
            ReservationResult.addErrorMessage("No reservations found for this email");
        } else {
            result.setReservations(reservations);
            result.setSuccess(true);
        }return result;
    }

    public ReservationResult<Reservation> add(Reservation reservation) throws DataAccessException {
        ReservationResult<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        getValidatedTotal(reservation);
        repository.add(reservation);
        result.setReservation(reservation);
        return result;
    }

    public ReservationResult<Reservation> update(Reservation reservation) {
        ReservationResult<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        getValidatedTotal(reservation);
        repository.update(reservation);
        result.setReservation(reservation);
        return result;
    }

    public ReservationResult<Reservation> delete(Reservation reservation) throws DataAccessException {
        ReservationResult<Reservation> result = new ReservationResult<>();
        boolean deleted = repository.delete(reservation);
        if (deleted) {
            result.setReservation(reservation);
            result.setSuccess(true);
            return result;
        } else {
            ReservationResult.addErrorMessage("Reservation not found");
            return result;
        }
    }

    private ReservationResult<Reservation> validate(Reservation reservation) {
        ReservationResult<Reservation> result = new ReservationResult<>();
        if (reservation == null) {
            ReservationResult.addErrorMessage("Reservation does not exist");
            return result;
        }
        if (reservation.getStart_date() == null || reservation.getEnd_date() == null) {
            ReservationResult.addErrorMessage("Start date and end date are required");
        }
        if (reservation.getStart_date().isBefore(LocalDate.now())) {
            ReservationResult.addErrorMessage("Start date cannot be in the past");
        }
        if (reservation.getStart_date().isAfter(reservation.getEnd_date())) {
            ReservationResult.addErrorMessage("Start date must be before end date");
        }
        for (Reservation r : repository.findAll()){
            if (reservation.getReservation_id() == r.getReservation_id()){
                continue;
            }
            if (reservation.getStart_date().isBefore(r.getEnd_date()) && reservation.getEnd_date().isAfter(r.getStart_date())) {
                ReservationResult.addErrorMessage("Reservation conflicts with existing reservation");
                break;
            }
        }
        if (result.getErrorMessages().isEmpty()) {
            result.setSuccess(true);
        } else {
            System.out.println(result.getErrorMessages());
        }
        return result;
    }


    public BigDecimal calculateTotal(LocalDate start_date, LocalDate end_date, BigDecimal standard_rate, BigDecimal
            weekend_rate) throws DataAccessException {
        BigDecimal total = BigDecimal.ZERO;
        LocalDate current_date = start_date;
        while (!current_date.isAfter(end_date)) {
            DayOfWeek day = current_date.getDayOfWeek();
            if (day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY) {
                total = total.add(weekend_rate);
            } else {
                total = total.add(standard_rate);
            }
            current_date = current_date.plus(1, ChronoUnit.DAYS);
        }
        return total;
    }

    public ReservationResult<Reservation> getValidatedTotal(Reservation reservation) throws DataAccessException {
        ReservationResult<Reservation> result = new ReservationResult<>();
        Location location = locationRepository.findById(reservation.getLocation().getLocation_id());
        if (location == null) {
            ReservationResult.addErrorMessage("Location not found");
            return result;
        }
        BigDecimal standard_rate = location.getStandard_rate();
        BigDecimal weekend_rate = location.getWeekend_rate();
        if (standard_rate == null || weekend_rate == null) {
            ReservationResult.addErrorMessage("There are no rates for this location");
            return result;
        }
        BigDecimal total = calculateTotal(reservation.getStart_date(), reservation.getEnd_date(), standard_rate, weekend_rate);
        reservation.setTotal(total);
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            ReservationResult.addErrorMessage("Total must be greater than or equal to zero");
            return result;
        }
        result.setReservation(reservation);
        return result;
    }

}















































