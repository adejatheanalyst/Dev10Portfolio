package learn.Data;

import learn.Data.Mappers.ReservationMapper;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ReservationJdbcClientRepository implements ReservationRepository {
    private final JdbcClient jdbcClient;

    public ReservationJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    final String findAll = "select r.reservation_id, r.guest_user_id, l.location_id, l.address, l.city, l.postal_code, l.state_id, l.standard_rate, l.weekend_rate, u.user_id, u.first_name, u.last_name, u.email, u.phone, r.start_date, r.end_date, r.total\n" +
            "                from reservation r\n" +
            "                inner join location l on r.location_id = l.location_id\n" +
            "                inner join user u on r.guest_user_id = u.user_id";

    @Override
    public Reservation findById(int id) throws DataAccessException {
        final String sql = findAll + " where r.reservation_id = ?;";

        return jdbcClient.sql(sql)
                .param(id)
                .query(new ReservationMapper())
                .optional().orElse(null);
    }

    @Override
    public List<Reservation> findByLocationId(Location location) throws DataAccessException {
        final String sql = findAll + " where l.location_id = :location_id;";
        long location_id = location.getLocation_id();
        return jdbcClient.sql(sql)
                .param("location_id", location_id)
                .query(new ReservationMapper())
                .list();
    }

    @Override
    public List<Reservation> findByUserId(User user_id) throws DataAccessException {
        final String sql = findAll + " where l.user_id = ?;";
        return jdbcClient.sql(sql)
                .param(user_id)
                .query(new ReservationMapper())
                .list();
    }

    @Override
    public List<Reservation> findAll() throws DataAccessException {
        final String sql = findAll + " order by r.end_date;";
        return jdbcClient.sql(sql)
                .query(new ReservationMapper())
                .list();
    }

    @Override
    public List<Reservation> findByDate(LocalDate start_date, LocalDate end_date) throws DataAccessException {
        final String sql = findAll + " where r.start_date <= ? and r.end_date >= ?;";
        return jdbcClient.sql(sql)
                .param(start_date)
                .param(end_date)
                .query(new ReservationMapper())
                .list();
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        final String sql = """
                insert into reservation (location_id, guest_user_id , start_date, end_date, total)
                values ((select location_id from location  where user_id = :user_id),
                        (select user_id from user where email = :email),
                        :start_date, :end_date, :total);
                """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        Integer user_id = (reservation.getLocation() instanceof Location)
                ? ((Location) reservation.getLocation()).getUser_id().getUser_id()
                : reservation.getLocation_id();

        String email = (reservation.getUser() instanceof User)
                ? ((User) reservation.getUser()).getEmail()
                : reservation.getUser().toString();
        jdbcClient.sql(sql)
                .param("user_id", user_id)
                .param("email", email.trim())
                .param("start_date", reservation.getStart_date())
                .param("end_date", reservation.getEnd_date())
                .param("total", reservation.getTotal())
                .update(keyHolder, "reservation_id");
        int reservation_id = (Objects.requireNonNull(keyHolder.getKey())).intValue();
        reservation.setReservation_id(reservation_id);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        final String sql = """
                update reservation set
                    start_date = :start_date,
                    end_date = :end_date,
                    total = :total
                where reservation_id = :reservation_id;
                """;

        return jdbcClient.sql(sql)
                .param("start_date", reservation.getStart_date())
                .param("end_date", reservation.getEnd_date())
                .param("total", reservation.getTotal())
                .param("reservation_id", reservation.getReservation_id())
                .update() > 0;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataAccessException {
        final String sql = "delete from reservation where reservation_id = ?;";
        return jdbcClient.sql(sql)
                .param(reservation.getReservation_id())
                .update() > 0;
    }

    @Override
    public List<Reservation> findByEmail(String email) throws DataAccessException {
        final String sql = findAll + " where u.email = ?;";
        return jdbcClient.sql(sql)
                .param(email)
                .query(new ReservationMapper())
                .list();
    }



}


