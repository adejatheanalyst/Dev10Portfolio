package learn;

import learn.Model.Location;
import learn.Model.User;
import learn.Model.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestData {
    public static final Location LOCATION = makeLocation();
    public static final Location LOCATION2 = makeLocation2();
    public static final Location LOCATION3 = makeLocation3();
    public static final Location NonExistingLocation = new Location();
    public final static User user = makeUser();
    public final static User user2 = makeUser2();
    public final static User user3 = makeUser3();
    public static final Reservation RESERVATION = makeReservation();
    public static final Reservation RESERVATION2 = makeReservation2();
    public static final Reservation RESERVATION3 = makeReservation3();
    public static final int existingUserId = 2;
    public static final int NonExistingUserId = 6;
    public static final String existingUserEmail = "testemail3@email.com";
    public static final String nonExistingUserEmail = "466@hruii.com";
    public static final String existingHostEmail = "alardeux4@nhs.uk";
    public static final int existingLocationId = 1;
    public static final int NonExistingLocationId = 6;
    public static final int existingReservationId = 1;
    public static final int NonExistingReservationId = 6;
    public static final LocalDate NonExistingStartDate = LocalDate.of(2022, 1, 1);
    public static final LocalDate NonExistingEndDate = LocalDate.of(2022, 1, 2);
    public static final LocalDate existingStartDate = LocalDate.of(2023, 1, 1);
    public static final LocalDate existingEndDate = LocalDate.of(2023, 1, 2);
    public static final String existingCity = "New York";
    public static final String NonExistingCity = "JamesTown";
    public static User NonExistingUser = new User();
    public static String existingReservationEmail ="testemail@email.com";


    public static Location makeLocation() {
        Location location = new Location();
        location.setLocation_id(1);
        User user = new User();
        user.setUser_id(1);
        location.setUser_id(user);
        location.setPostal_code("4465446");
        location.setAddress("545 West view ave");
        location.setCity("Atlanta");
        location.setStateId(9);
        location.setStandard_rate(BigDecimal.valueOf(110));
        location.setWeekend_rate(BigDecimal.valueOf(127));
        return location;
    }

    private static Location makeLocation2() {
        Location location = new Location();
        location.setLocation_id(2);
        User user = new User();
        user.setUser_id(2);

        location.setUser_id(user);
        location.setPostal_code("06704");
        location.setAddress("1301 hokie pokie st");
        location.setCity("New York");
        location.setStateId(2);
        location.setStandard_rate(BigDecimal.valueOf(150));
        location.setWeekend_rate(BigDecimal.valueOf(160));
        return location;
    }

    private static Location makeLocation3() {
        Location location = new Location();
        location.setLocation_id(3);
        User user = new User();
        user.setUser_id(3);
        location.setUser_id(user);
        location.setPostal_code("1111");
        location.setAddress("5 test drive");
        location.setCity("Panama City");
        location.setStateId(8);
        location.setStandard_rate(BigDecimal.valueOf(245));
        location.setWeekend_rate(BigDecimal.valueOf(263));

        return location;
    }

    private static User makeUser() {
        User user1 = new User();
        user1.setUser_id(1);
        user1.setFirst_name("Sam");
        user1.setLast_name("Evans");
        user1.setEmail("testemail@email.com");
        user1.setPhone("555-888-8263");
        return user1;
    }

    private static User makeUser2() {
        User user1 = new User();
        user1.setUser_id(2);
        user1.setFirst_name("James");
        user1.setLast_name("Bond");
        user1.setEmail("testemail2@email.com");
        user1.setPhone("852-000-8263");
        return user1;
    }

    private static User makeUser3() {
        User user1 = new User();
        user1.setUser_id(3);
        user1.setFirst_name("Spring");
        user1.setLast_name("Water");
        user1.setEmail("testemail3@email.com");
        user1.setPhone("203-888-8896");
        return user1;
    }

    public static Reservation makeReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservation_id(1);
        reservation.setUser(makeUser());
        String email = makeUser().getEmail();
        reservation.setEmail(email);
        reservation.setUser(user);
        reservation.setLocation(LOCATION);
        reservation.setStart_date(LocalDate.of(2021, 1, 1));
        reservation.setEnd_date(LocalDate.of(2021, 1, 2));
        reservation.setStandard_rate(LOCATION.getStandard_rate());
        reservation.setWeekend_rate(LOCATION.getWeekend_rate());
        return reservation;
    }

    public static Reservation makeReservation2() {
        Reservation reservation = new Reservation();
        reservation.setReservation_id(2);
        reservation.setUser(makeUser2());
        String email = makeUser2().getEmail();
        reservation.setEmail(email);
        reservation.setLocation(LOCATION2);
        reservation.setStart_date(LocalDate.of(2021, 2, 1));
        reservation.setEnd_date(LocalDate.of(2021, 2, 2));
        reservation.setStandard_rate(LOCATION2.getStandard_rate());
        reservation.setWeekend_rate(LOCATION2.getWeekend_rate());
        return reservation;
    }

    public static Reservation makeReservation3() {
        Reservation reservation = new Reservation();
        reservation.setReservation_id(3);
        reservation.setUser(makeUser3());
        String email = makeUser3().getEmail();
        reservation.setEmail(email);
        reservation.setLocation(LOCATION3);
        reservation.setStart_date(LocalDate.of(2025, 3, 1));
        reservation.setEnd_date(LocalDate.of(2025, 3, 3));
        BigDecimal sr = LOCATION3.getStandard_rate();
        BigDecimal wr = LOCATION3.getWeekend_rate();
        reservation.setStandard_rate(sr);
        reservation.setWeekend_rate(wr);

        return reservation;
    }
}

