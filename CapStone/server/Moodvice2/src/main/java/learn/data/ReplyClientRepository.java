package learn.data;

import learn.data.mappers.ReplyMapper;
import learn.models.Reply;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.param;

@Repository
public class ReplyClientRepository implements ReplyRepository{
    private  final JdbcClient jdbcClient;
    private static final String selectAll = "select replyId, title, body, userId, moodId, moodViceId, created_at from moodViceReply";

    public ReplyClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Reply findByReplyId(int replyId) {
       final String sql = selectAll + " where replyId = ?";
       return jdbcClient.sql(sql)
               .param(replyId)
               .query(new ReplyMapper())
               .optional().orElse(null);
    }

    @Override
    public List<Reply> findByReplyMoodViceId(int moodViceId) {
        final String sql = selectAll + " where moodViceId = ?";
        return jdbcClient.sql(sql)
                .param(moodViceId)
                .query(new ReplyMapper())
                .list();
    }

    @Override
    public List<Reply> findByReplyUserId(int userId) {
        final String sql = selectAll + " where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new ReplyMapper())
                .list();
    }

    @Override
    public Reply findByKey(int replyId, int userId, int moodViceId) {
        final String sql = selectAll + " where replyId = ? and userId = ? and moodViceId = ?;";
        return jdbcClient.sql(sql)
                .param(replyId)
                .param(userId)
                .param(moodViceId)
                .query(new ReplyMapper())
                .optional().orElse(null);
    }

    @Override
    public Reply addReply(Reply reply) {
        final  String sql = "insert into moodViceReply(title, body, userId, moodId, moodViceId) values (:title, :body, :userId, :moodId, :moodViceId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcClient.sql(sql)
                .param("title", reply.getTitle())
                .param("title", reply.getTitle())
                .param("body", reply.getBody())
                .param("userId", reply.getUserId())
                .param("moodId", reply.getMoodId())
                .param("moodViceId", reply.getMoodViceId())
                .update(keyHolder, "replyId");

        if(rowAffected <= 0){
            return null;
        }
            reply.setReplyId(keyHolder.getKey().intValue());
        return reply;

    }

    @Override
    public boolean updateReply(Reply reply) {
        final String sql = "update moodViceReply set title = :title, body = :body where replyId = :replyId";
        return jdbcClient.sql(sql)
                .param("title", reply.getTitle())
                .param("body", reply.getBody())
                .param("replyId", reply.getReplyId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int replyId) {
        final String sql = "delete from moodViceReply where replyId = ?";
        return jdbcClient.sql(sql)
                .param(replyId)
                .update() > 0;
    }
}
