package learn.data;

import learn.models.Reply;

import java.util.List;

public interface ReplyRepository {
    Reply findByReplyId(int replyId);
    List<Reply> findByReplyMoodViceId(int moodViceId);
    List<Reply> findByReplyUserId(int userId);
    Reply findByKey(int replyId, int userId, int moodViceId);
    Reply addReply(Reply reply);
    boolean updateReply(Reply reply);
    boolean deleteById(int replyId);
}
