package learn.data;

import learn.data.mappers.MoodViceMapper;
import learn.domain.Results.MoodViceResult;
import learn.models.MoodType;
import learn.models.MoodVice;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sun.tools.attach.VirtualMachine.list;
@Repository
public class MoodViceClientRepository implements MoodViceRepository{
    private static final String selectAll = "select moodViceId, title, body, userId, moodId, created_at from moodVice";

    private final JdbcClient jdbcClient;

    public MoodViceClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<MoodVice> findAll() {
        return jdbcClient.sql(selectAll)
                .query(new MoodViceMapper())
                .list();
    }

    @Override
    public List<MoodVice> findByMoodType(int moodType) {
        return jdbcClient.sql(selectAll + " where moodId = ?")
                .param(moodType)
                .query(new MoodViceMapper())
                .list();
    }

    @Override
    public MoodVice findByMoodViceId(int moodViceId) {
        final String sql = selectAll + " where moodViceId = ?";
        return jdbcClient.sql(sql)
                .param(moodViceId)
                .query(new MoodViceMapper())
                .optional().orElse(null);
    }

    @Override
    public List<MoodVice> findByUserId(int userId) {
        final String sql = selectAll + " where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new MoodViceMapper())
                .list();
    }

    @Override
    public MoodVice create(MoodVice moodVice) {
        final String sql = "insert into moodVice (title, body, userId, moodId) values (:title, :body, :userId, :moodId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcClient.sql(sql)
                .param("title", moodVice.getTitle())
                .param("body",  moodVice.getBody())
                .param("userId",  moodVice.getUserId())
                .param("moodId",  moodVice.getMoodId())
                .update(keyHolder, "moodViceId");

        if (rowAffected <= 0) {
            return null;
        }
        moodVice.setMoodViceId(keyHolder.getKey().intValue());
        return  moodVice;
    }

    @Override
    public boolean update(MoodVice moodVice) {
        final String sql = "update moodVice set title = :title, body = :body where moodViceId = :moodViceId";
        return jdbcClient.sql(sql)
                .param("moodViceId", moodVice.getMoodViceId())
                .param("title", moodVice.getTitle())
                .param("body", moodVice.getBody())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int moodViceId) {
        final String sql = "delete from moodVice where moodViceId = ?";
        return jdbcClient.sql(sql)
                .param(moodViceId)
                .update() > 0;
    }

}
