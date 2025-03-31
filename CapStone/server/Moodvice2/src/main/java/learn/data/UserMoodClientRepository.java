package learn.data;

import learn.data.mappers.UserMoodsMapper;
import learn.models.UserMoods;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserMoodClientRepository implements UserMoodRepository {
    private final JdbcClient jdbcClient;

    public UserMoodClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public UserMoods create(UserMoods userMood) {
        final String sql ="insert into userMoods (userId, moodId, mood_notes) values (:userId, :moodId, :mood_notes)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcClient.sql(sql)
                .param("userId", userMood.getUserId())
                .param("moodId", userMood.getMoodId())
                .param("mood_notes", userMood.getMood_notes())
                .update(keyHolder, "userMoodId");
        if(rowsAffected <= 0) {
            return null;
        }
        userMood.setUserMoodId(keyHolder.getKey().intValue());
        return userMood;
    }

    @Override
    public List<UserMoods> findByMoodId(int moodId) {
        final String sql = "select userMoodId, userId, moodId, mood_notes, created_at from userMoods where moodId = ?";
        return jdbcClient.sql(sql)
                .param(moodId)
                .query(new UserMoodsMapper())
                .list();
    }

    @Override
    public List<UserMoods> findUserMoodsByMonth(int moodId, int month) {
        final String sql = "select userMoodId, userId, moodId, mood_notes, created_at from userMoods  where month(created_at) = ? and moodId = ?";
        return jdbcClient.sql(sql)
                .param(moodId)
                .param(month)
                .query(new UserMoodsMapper())
                .list();
    }

    @Override
    public List<UserMoods> findByUserId(int userId) {
        final String sql = "select userMoodId, userId, moodId, mood_notes, created_at from userMoods where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new UserMoodsMapper())
                .list();
    }

    @Override
    public List<UserMoods> findAll() {
        final String sql = "select userMoodId, userId, moodId, mood_notes, created_at from userMoods";
        return jdbcClient.sql(sql)
                .query(new UserMoodsMapper())
                .list();
    }

    @Override
    public List<UserMoods> findByDay(LocalDate date, int userId) {
        final String sql = "select userMoodId, userId, moodId, mood_notes, created_at from userMoods where created_at = ? and userId = ?";
        return jdbcClient.sql(sql)
                .param(date)
                .param(userId)
                .query(new UserMoodsMapper())
                .list();
    }


}