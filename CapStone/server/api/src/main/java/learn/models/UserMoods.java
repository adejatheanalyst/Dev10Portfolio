package learn.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserMoods {
    private int userMoodId;
    private int userId;
    private int moodId;
    private String mood_notes;
    private LocalDate created_at;

    public UserMoods() {
    }
    public UserMoods(int moodId){
        this.moodId = moodId;
    }
    public UserMoods(int userMoodId, int userId, int moodId, String mood_notes, LocalDate created_at) {
        this.userMoodId = userMoodId;
        this.userId = userId;
        this.moodId = moodId;
        this.mood_notes = mood_notes;
        this.created_at = created_at;
    }


    public int getUserMoodId() {
        return userMoodId;
    }

    public void setUserMoodId(int userMoodId) {
        this.userMoodId = userMoodId;
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

    public String getMood_notes() {
        return mood_notes;
    }

    public void setMood_notes(String mood_notes) {
        this.mood_notes = mood_notes;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserMoods userMoods = (UserMoods) o;
        return userMoodId == userMoods.userMoodId && userId == userMoods.userId && moodId == userMoods.moodId && Objects.equals(mood_notes, userMoods.mood_notes) && Objects.equals(created_at, userMoods.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMoodId, userId, moodId, mood_notes, created_at);
    }

    @Override
    public String toString() {
        return "UserMoods{" +
                "userMoodId=" + userMoodId +
                ", userId=" + userId +
                ", moodId=" + moodId +
                ", mood_notes='" + mood_notes + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
