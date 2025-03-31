package learn.Data.Mappers;

import learn.Model.Location;
import learn.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeekendRateMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setLocation_id(rs.getInt("location_id"));
        location.setWeekend_rate(rs.getBigDecimal("weekend_rate"));
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        location.setUser_id(user);
        return location;
    }
}

