package learn.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reply {
    private int replyId;
    private String title;
    private String body;
    private int userId;
    private int moodId;
    private int moodViceId;
    private LocalDate created_at;


    public Reply() {
    }

    public Reply(int replyId, String title, String body, int userId, int moodId, int moodViceId, LocalDate created_at) {
        this.replyId = replyId;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.moodId = moodId;
        this.moodViceId = moodViceId;
        this.created_at = created_at;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMoodViceId() {
        return moodViceId;
    }

    public void setMoodViceId(int moodViceId) {
        this.moodViceId = moodViceId;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
    public String getKey() {
        return String.format("%s-%s-%s", replyId, userId, moodViceId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return replyId == reply.replyId && userId == reply.userId && moodId == reply.moodId && moodViceId == reply.moodViceId && Objects.equals(title, reply.title) && Objects.equals(body, reply.body) && Objects.equals(created_at, reply.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyId, title, body, userId, moodId, moodViceId, created_at);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", moodId=" + moodId +
                ", moodViceId=" + moodViceId +
                ", created_at=" + created_at +
                '}';
    }
}
