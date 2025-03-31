package learn.data.mappers;

import learn.models.Resources;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapper implements RowMapper<Resources> {
    @Override
    public Resources mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resources resource = new Resources();
        resource.setResourceId(rs.getInt("resourceId"));
        resource.setTitle(rs.getString("title"));
        resource.setResourceUrl(rs.getString("resourceUrl"));
        resource.setMoodId(rs.getInt("moodId"));
        return resource;
    }
}
