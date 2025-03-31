package learn.Data;

import learn.DataHelper;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationJdbcClientRepositoryTest {
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    ReservationJdbcClientRepository repository = new ReservationJdbcClientRepository(jdbcClient);
    UserJdbcClientRepository userRepository = new UserJdbcClientRepository(jdbcClient);
    LocationJdbcClientRepository locationRepository = new LocationJdbcClientRepository(jdbcClient);
String nonExistingEmail = "test@test.com";
String existingGuestEmail = "bhouliston8@mozilla.com";
String existingHostEmail = "thonnan2@berkeley.edu";
String currentReservationEmail = "lvondra0@vkontakte.ru";

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }
    @Test
    void findById() {
        Reservation expected = new Reservation();
        Reservation actual = repository.findById(1);
        assertEquals(1, actual.getReservation_id());
        assertEquals("2023-01-01", actual.getStart_date().toString());
    }
    @Test
    void findByIdFail() {
        Reservation expected = new Reservation();
        Reservation actual = repository.findById(6);
        assertEquals(null, actual);
    }


    @Test
    void findByLocation() {
        Location location = locationRepository.findById(1);
        List<Reservation> expected = repository.findByLocationId(location);
        assertEquals(1 , expected.size());
        assertEquals(1, expected.get(0).getReservation_id());
    }
    @Test
    void findByLocationIdFail() {
        Location location = locationRepository.findById(6);
        List<Reservation> expected = repository.findByLocationId(location);
        assertEquals(0 , expected.size());
    }

    @Test
    void findByUserID() {
        User user = userRepository.findByEmail(currentReservationEmail);
        List<Reservation> expected = repository.findByUserId(user);
        assertEquals(1 , expected.size());
        assertEquals(1, expected.get(0).getReservation_id());
    }
    @Test
    void findByUserIDFail() {
        User user = userRepository.findByEmail(nonExistingEmail);
        List<Reservation> expected = repository.findByUserId(user);
        assertEquals(0 , expected.size());
    }

    @Test
    void findAll() {
        List<Reservation> expected = repository.findAll();
        assertEquals(5, expected.size());
    }

    @Test
    void findByDate() {
        List<Reservation> expected = repository.findByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3));
        assertEquals(1, expected.size());
        assertEquals(1, expected.get(0).getReservation_id());
    }
    @Test
    void findByDateFail() {
        List<Reservation> expected = repository.findByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 17));
        assertEquals(0, expected.size());
    }
@Nested class AddTests {
    @Test
    void add() {
        User guest = userRepository.findByEmail(existingGuestEmail);
        User host = userRepository.findByEmail(existingHostEmail);


        List<Location> location = locationRepository.findByUserId(host);
        Reservation reservation = new Reservation();
        Location hostLo = location.get(0);

        reservation.setLocation(hostLo);
        reservation.setUser(guest);
        reservation.setStart_date(LocalDate.of(2024, 06, 1));
        reservation.setEnd_date(LocalDate.of(2024, 06, 3));
        reservation.setTotal(BigDecimal.valueOf(300));


        Reservation actual = repository.add(reservation);
        assertNotNull(actual);
        assertEquals(6, actual.getReservation_id());
        assertEquals(guest.getUser_id(), actual.getUser().getUser_id());
        assertEquals(hostLo.getLocation_id(), actual.getLocation().getLocation_id());
    }
@Test
    void addFailNonexistentGuestEmail() {
        User guest = userRepository.findByEmail(nonExistingEmail);
        User host = userRepository.findByEmail(existingHostEmail);
        List<Location> location = locationRepository.findByUserId(host);

        Reservation reservation = new Reservation();
        Location hostLo = location.get(0);

        reservation.setLocation(hostLo);
        reservation.setUser(guest);
        reservation.setStart_date(LocalDate.of(2024, 06, 1));
        reservation.setEnd_date(LocalDate.of(2024, 06, 3));
        reservation.setTotal(BigDecimal.valueOf(300));

    Reservation actual = repository.add(reservation);
    assertNotNull(actual);
    assertEquals(6, actual.getReservation_id());
    assertEquals(guest.getUser_id(), actual.getUser().getUser_id());
    assertEquals(hostLo.getLocation_id(), actual.getLocation().getLocation_id());

    }
    @Test
    void addFailNonexistentHostEmail() {
        User guest = userRepository.findByEmail(existingGuestEmail);
        User host = userRepository.findByEmail(nonExistingEmail);
        assertNull(host);
        assertThrows(NullPointerException.class, () -> {
            List<Location> location = locationRepository.findByUserId(host);
        });
    }
}
@Nested class UpdateTests {
    @Test
    void update() {
        LocalDate start_date = LocalDate.of(2024, 10, 1);
        LocalDate end_date = LocalDate.of(2024, 10, 3);
        BigDecimal total = BigDecimal.valueOf(300);
        List<Reservation> guest = repository.findByEmail(existingGuestEmail);
        guest.get(0).setStart_date(start_date);
        guest.get(0).setEnd_date(end_date);
        guest.get(0).setTotal(total);
        boolean actual = repository.update(guest.get(0));
        assertTrue(actual);
    }
    @Test
        void updateFailNonexistentEmail() {
        LocalDate start_date = LocalDate.of(2024, 10, 1);
        LocalDate end_date = LocalDate.of(2024, 10, 3);
        BigDecimal total = BigDecimal.valueOf(300);
        List<Reservation> guest = repository.findByEmail(nonExistingEmail);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            boolean actual = repository.update(guest.get(0));
        });

    }
}
@Nested class DeleteTests {
    @Test
    void delete() {
        Reservation guest = repository.findById(1);
        boolean actual = repository.delete(guest);
        assertTrue(actual);
    }
    @Test
    void deleteFail() {
        Reservation guest = repository.findById(6);
        assertThrows(NullPointerException.class, () -> {
            boolean actual = repository.delete(guest);
            assertFalse(actual);
        });


    }
}
@Nested class FindByUserTests {
    @Test
    void findByEmail() {
        List<Reservation> expected = repository.findByEmail(currentReservationEmail);
        assertEquals(1, expected.size());
        assertEquals(1, expected.get(0).getReservation_id());
    }
    @Test
    void findByEmailFail() {
        List<Reservation> expected = repository.findByEmail(existingGuestEmail);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            assertEquals(0, expected.size());
            assertEquals(0, expected.get(0).getReservation_id());
        });
    }

    }
}


