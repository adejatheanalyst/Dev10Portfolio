package learn.Data.Mappers;

import learn.Model.Location;
import learn.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {

        Location location = new Location();
        location.setLocation_id(rs.getInt("location_id"));

        try {
            location.setStandard_rate(rs.getBigDecimal("standard_rate"));
            location.setWeekend_rate(rs.getBigDecimal("weekend_rate"));
            location.setAddress(rs.getString("address"));
            location.setCity(rs.getString("city"));
            location.setPostal_code(rs.getString("postal_code"));
            location.setStateId(rs.getInt("state_id"));
        } catch (SQLException ignored){
        }

        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        location.setUser_id(user);

        return location;
    }
}

