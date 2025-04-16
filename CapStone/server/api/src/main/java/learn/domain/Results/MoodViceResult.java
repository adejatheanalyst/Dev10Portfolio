package learn.domain.Results;

import learn.models.MoodVice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoodViceResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private MoodVice moodVice;
    private int userId;
    private int moodId;
    List<MoodVice> moodVices;

    public MoodViceResult(MoodVice moodVice) {
        this.moodVice = moodVice;
    }
    public MoodViceResult() {
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

    public MoodVice getMoodVice() {
        return moodVice;
    }

    public void setMoodVice(MoodVice moodVice) {
        this.moodVice = moodVice;
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
        MoodViceResult that = (MoodViceResult) o;
        return userId == that.userId && moodId == that.moodId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(moodVice, that.moodVice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, moodVice, userId, moodId);
    }

    @Override
    public String toString() {
        return "MoodViceResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", moodVice=" + moodVice +
                ", userId=" + userId +
                ", moodId=" + moodId +
                '}';
    }

    public List<MoodVice> getMoodVices() {
        return moodVices;
    }

    public void setMoodVices(List<MoodVice> moodVices) {
        this.moodVices = moodVices;
    }
}
