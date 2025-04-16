package learn.data.mappers;

import learn.models.MoodType;
import learn.models.MoodVice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoodViceMapper implements RowMapper<MoodVice> {

    @Override
    public MoodVice mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoodVice moodVice = new MoodVice();
        moodVice.setMoodViceId(rs.getInt("moodViceId"));
        moodVice.setTitle(rs.getString("title"));
        moodVice.setBody(rs.getString("body"));
        moodVice.setUserId(rs.getInt("userId"));
        moodVice.setMoodId(rs.getInt("moodId"));
        moodVice.setCreated_at(rs.getDate("created_at").toLocalDate());
        return moodVice;
    }
}
