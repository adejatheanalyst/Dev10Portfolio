package learn.data.mappers;

import learn.models.Reply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
      Reply reply = new Reply();
      reply.setReplyId(rs.getInt("replyId"));
      reply.setTitle(rs.getString("title"));
        reply.setBody(rs.getString("body"));
        reply.setUserId(rs.getInt("userId"));
        reply.setMoodId(rs.getInt("moodId"));
        reply.setMoodViceId(rs.getInt("moodViceId"));
        reply.setCreated_at(rs.getDate("created_at").toLocalDate());
        return reply;
    }
}
