package learn.domain.Results;

import learn.models.UserMoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMoodResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private UserMoods userMoods;
    private int userId;
    private int moodId;
    List<UserMoods> userMoodsList;

    public UserMoodResult() {
    }

    public UserMoodResult(UserMoods userMoods) {
        this.userMoods = userMoods;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public ArrayList<String> getMessages() {
        return messages;
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

    public List<UserMoods> getUserMoodsList() {
        return userMoodsList;
    }

    public void setUserMoodsList(List<UserMoods> userMoodsList) {
        this.userMoodsList = userMoodsList;
    }

    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }
    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));
    }
    public boolean isSuccess() {
        // If result type is success, the operation was successful;
        return this.resultType == ResultType.SUCCESS;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public UserMoods getUserMoods() {
        return userMoods;
    }

    public void setUserMoods(UserMoods userMoods) {
        this.userMoods = userMoods;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserMoodResult result = (UserMoodResult) o;
        return userId == result.userId && moodId == result.moodId && Objects.equals(messages, result.messages) && resultType == result.resultType && Objects.equals(userMoods, result.userMoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, userMoods, userId, moodId);
    }

    @Override
    public String toString() {
        return "UserMoodResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", userMoods=" + userMoods +
                ", userId=" + userId +
                ", moodId=" + moodId +
                '}';
    }
}
