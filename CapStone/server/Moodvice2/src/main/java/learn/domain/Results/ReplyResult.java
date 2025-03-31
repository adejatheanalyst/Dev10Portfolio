package learn.domain.Results;

import learn.models.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReplyResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private Reply reply;
    private int userId;
    private int moodId;
    private int replyId;
    private int moodViceId;
    List<Reply> replyList;

    public ReplyResult(Reply reply) {
        this.reply = reply;
    }

    public ReplyResult() {
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getMoodViceId() {
        return moodViceId;
    }

    public void setMoodViceId(int moodViceId) {
        this.moodViceId = moodViceId;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }
    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));
    }
    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public boolean isSuccess() {
        return this.resultType == ResultType.SUCCESS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReplyResult that = (ReplyResult) o;
        return userId == that.userId && moodId == that.moodId && replyId == that.replyId && moodViceId == that.moodViceId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(reply, that.reply) && Objects.equals(replyList, that.replyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, reply, userId, moodId, replyId, moodViceId, replyList);
    }

    @Override
    public String toString() {
        return "ReplyResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", reply=" + reply +
                ", userId=" + userId +
                ", moodId=" + moodId +
                ", replyId=" + replyId +
                ", moodViceId=" + moodViceId +
                ", replyList=" + replyList +
                '}';
    }
}
