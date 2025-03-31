package learn.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class MoodVice {
    private int moodViceId;
    private String title;
    private String body;
    private int moodId;
    private int userId;
    private LocalDate created_at;


    public MoodVice(int moodViceId, String title, String body, int userId, int moodId, LocalDate created_at) {
        this.moodViceId = moodViceId;
        this.title = title;
        this.body = body;
        this.moodId = moodId;
        this.userId = userId;
        this.created_at = created_at;
    }

    public MoodVice() {
    }

    public int getMoodViceId() {
        return moodViceId;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public void setMoodViceId(int moodViceId) {
        this.moodViceId = moodViceId;
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

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

//    public String getMoodVice(){
//        return String.format("%s %s ", title, body);
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MoodVice moodVice = (MoodVice) o;
        return moodViceId == moodVice.moodViceId && moodId == moodVice.moodId && userId == moodVice.userId && Objects.equals(title, moodVice.title) && Objects.equals(body, moodVice.body) && Objects.equals(created_at, moodVice.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moodViceId, title, body, moodId, userId, created_at);
    }

    @Override
    public String toString() {
        return "MoodVice{" +
                "moodViceId=" + moodViceId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", moodId=" + moodId +
                ", userId=" + userId +
                ", created_at=" + created_at +
                '}';
    }
}
