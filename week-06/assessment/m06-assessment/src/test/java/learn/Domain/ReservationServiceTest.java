package learn.Domain;

import learn.Data.LocationRepositoryDouble;
import learn.Data.ReservationDouble;
import learn.Model.Location;
import learn.Model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import learn.TestData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static learn.TestData.makeReservation;
import static learn.TestData.makeReservation3;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    ReservationService service;
    private ReservationDouble repository = new ReservationDouble();

    //    ReservationService service = new ReservationService(new ReservationDouble());
    @BeforeEach
    void setUp() {
        service = new ReservationService(new LocationRepositoryDouble(), repository);
    }

    @Test
    void findAll() {
        List<Reservation> reservations = service.findAll();
        assertEquals(2, reservations.size());
    }

    @Test
    void findById() {
        ReservationResult<Reservation> reservation = service.findById(1);
        assertEquals(1, reservation.getReservation().getReservation_id());
    }

    @Test
    void findByLocationId() {
        Location location = TestData.LOCATION;

        ReservationResult<Reservation> reservations = service.findByLocationId(location);
        assertNotNull(reservations);
        assertFalse(reservations.getReservations().isEmpty());
        assertEquals(1, reservations.getReservations().get(0).getReservation_id());
    }

    @Test
    void findByLocationIdFail() {
        Location location = new Location();
        location.setLocation_id(9);

        ReservationResult<Reservation> reservations = service.findByLocationId(location);
        List<Reservation> reservationsList = reservations.getReservations();
        assertTrue(reservationsList == null || reservationsList.isEmpty());
    }

    @Test
    void findByUser() {
        ReservationResult<Reservation> reservations = service.findByUser(TestData.user2);
        assertNotNull(reservations);
        assertFalse(reservations.getReservations().isEmpty());
        assertEquals(2, reservations.getReservations().get(0).getReservation_id());
    }

    @Test
    void findByDate() {
        LocalDate start_date = LocalDate.of(2021, 1, 1);
        LocalDate end_date = LocalDate.of(2021, 1, 2);

        ReservationResult<Reservation> reservations = service.findByDate(start_date, end_date);
        assertNotNull(reservations);
        assertFalse(reservations.getReservations().isEmpty());
        assertEquals(1, reservations.getReservations().get(0).getReservation_id());
    }

    @Test
    void findByDateFail() {
        LocalDate start_date = LocalDate.of(2022, 1, 1);
        LocalDate end_date = LocalDate.of(2022, 1, 2);

        ReservationResult<Reservation> reservations = service.findByDate(start_date, end_date);
        List<Reservation> reservationsList = reservations.getReservations();
        assertTrue(reservationsList == null || reservationsList.isEmpty());
    }

    @Test
    void add() {
        Reservation reservation = makeReservation3();
        ReservationResult<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
        assertNotNull(result.getReservation());
        assertEquals(reservation, result.getReservation());
    }

    @Test
    void calculateTotal() {
//        Reservation reservation = makeReservation3();
//        ReservationResult<Reservation> result = service.add(reservation);
        LocalDate start_date = LocalDate.of(2021, 1, 1);
        LocalDate end_date = LocalDate.of(2021, 1, 2);
        BigDecimal standard_rate = new BigDecimal(100);
        BigDecimal weekend_rate = new BigDecimal(200);
        BigDecimal total = service.calculateTotal(start_date, end_date, standard_rate, weekend_rate);
        assertEquals(new BigDecimal(400), total);
    }

    @Test
    void findByEmail() {
        ReservationResult<Reservation> reservations = service.findByEmail(TestData.existingUserEmail);
        assertNotNull(reservations);
        assertFalse(reservations.getReservations().isEmpty());
        assertEquals(3, reservations.getReservations().get(0).getReservation_id());
    }
@Nested class UpdateTests {
    @Test
    void update() {
        ReservationResult<Reservation> reservations = service.findByEmail(TestData.existingReservationEmail);

        Reservation reservation = reservations.getReservations().get(0);

        reservation.setStart_date(LocalDate.of(2025, 4, 1));
        reservation.setEnd_date(LocalDate.of(2025, 4, 2));

        ReservationResult<Reservation> result = new ReservationResult<>();
        result.setSuccess(true);
        result.setReservation(reservation);

        ReservationResult<Reservation> actual = service.update(reservation);
        assertTrue(result.isSuccess(), "Result is not successful");
        assertNotNull(result.getReservation(), "Reservation is null");
        assertEquals(reservation, result.getReservation(), "Reservation is not equal");
    }
    @Test
    void updateFailNonExistingEmail() {
        ReservationResult<Reservation> reservations = service.findByEmail(TestData.nonExistingUserEmail);
        assertThrows(NullPointerException.class, () -> {
            Reservation reservation = reservations.getReservations().get(0);
            ReservationResult<Reservation> result = service.update(reservation);
        });
    }
}
    @Nested
    class DeleteTests {
        @Test
        void delete() {
            ReservationResult<Reservation> reservations = service.findByEmail(TestData.existingReservationEmail);
            Reservation reservation = reservations.getReservations().get(0);

            ReservationResult<Reservation> result = service.delete(reservation);
            assertTrue(result.isSuccess());
        }
        @Test
        void deleteFail() {
            ReservationResult<Reservation> reservations = service.findByEmail(TestData.nonExistingUserEmail);
            assertThrows(NullPointerException.class, () -> {
                Reservation reservation = reservations.getReservations().get(0);
                ReservationResult<Reservation> result = service.delete(reservation);
            });

        }
    }
}