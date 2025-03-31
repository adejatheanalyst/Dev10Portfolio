package learn.Data.Mappers;

import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("guest_user_id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));

        Location location = new Location();
        try {
            location.setLocation_id(rs.getInt("location_id"));
        } catch (SQLException ex) {
            location.setLocation_id(0);
        }
        location.setUser_id(user);
        location.setAddress(rs.getString("address"));
        location.setCity(rs.getString("City"));
        location.setPostal_code(rs.getString("postal_code"));
        location.setStateId(Integer.parseInt(rs.getString("state_id")));
        location.setStandard_rate(rs.getBigDecimal("standard_rate"));
        location.setWeekend_rate(rs.getBigDecimal("weekend_rate"));

        Reservation reservation = new Reservation();
        reservation.setReservation_id(rs.getInt("reservation_id"));
        reservation.setLocation(location);
        reservation.setUser(user);
        reservation.setStart_date(rs.getDate("start_date").toLocalDate());
        reservation.setEnd_date(rs.getDate("end_date").toLocalDate());
        try {
            reservation.setTotal(rs.getBigDecimal("total"));
        } catch (SQLException ex) {
            reservation.setTotal(null);
        }
        return reservation;

    }
}
