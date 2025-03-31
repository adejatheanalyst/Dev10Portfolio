package learn.Data.Mappers;

import learn.Model.Location;
import learn.Model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CostMapper implements RowMapper<Reservation> {


    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setLocation_id(rs.getInt("location_id"));

        Reservation reservation = new Reservation();
        reservation.setLocation(location);
        reservation.setReservation_id(rs.getInt("reservation_id"));
        reservation.setStart_date(rs.getDate("start_date").toLocalDate());
        reservation.setEnd_date(rs.getDate("end_date").toLocalDate());
        return reservation;
    }

}
