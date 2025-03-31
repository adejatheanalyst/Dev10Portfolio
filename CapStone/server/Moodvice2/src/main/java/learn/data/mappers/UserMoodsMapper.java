package learn.data.mappers;

import learn.models.UserMoods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMoodsMapper implements RowMapper<UserMoods> {
    @Override
    public UserMoods mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserMoods userMoods = new UserMoods();
        userMoods.setUserMoodId(rs.getInt("userMoodId"));
        userMoods.setUserId(rs.getInt("userId"));
        userMoods.setMoodId(rs.getInt("moodId"));
        userMoods.setMood_notes(rs.getString("mood_notes"));
        userMoods.setCreated_at(rs.getDate("created_at").toLocalDate());
        return userMoods;
    }
}
