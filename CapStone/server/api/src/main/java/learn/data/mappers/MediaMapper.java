package learn.data.mappers;

import learn.models.Media;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaMapper implements RowMapper<Media> {
    @Override
    public Media mapRow(ResultSet rs, int rowNum) throws SQLException {
       Media media = new Media();
         media.setMediaId(rs.getInt("mediaId"));
            media.setMoodId(rs.getInt("moodId"));
            media.setMediaType(rs.getString("mediaType"));
            media.setContentUrl(rs.getString("contentUrl"));
            media.setDescription(rs.getString("description"));
            return media;
    }
}
