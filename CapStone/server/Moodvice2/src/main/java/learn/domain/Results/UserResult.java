package learn.domain.Results;

import learn.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private User user;
    private ResultType resultType = ResultType.SUCCESS;

    public ArrayList<String> getMessages() {
        return messages;
    }
    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
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

        public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResult that = (UserResult) o;
        return Objects.equals(messages, that.messages) && Objects.equals(user, that.user) && resultType == that.resultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, user, resultType);
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "messages=" + messages +
                ", user=" + user +
                ", resultType=" + resultType +
                '}';
    }
}
