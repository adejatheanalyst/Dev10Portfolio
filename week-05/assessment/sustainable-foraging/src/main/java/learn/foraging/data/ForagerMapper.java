package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForagerMapper implements RowMapper<Forager> {
    @Override
    public Forager mapRow(ResultSet rs, int rowNum) throws SQLException {
        Forager forager = new Forager();
        forager.setId(rs.getInt("forager_id"));
        forager.setFirstName(rs.getString("first_name"));
        forager.setLastName(rs.getString("last_name"));
        forager.setState(rs.getString("state_abbr"));
        return forager;
    }
}
