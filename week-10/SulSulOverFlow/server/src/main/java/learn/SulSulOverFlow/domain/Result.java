package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.models.Answers;
import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Result<T> {
    private T payload;
    private List<String> errorMessages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Result() {
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void addErrorMessage(String format, Object... args) {
        this.resultType = ResultType.INVALID;
        errorMessages.add(String.format(format, args));
    }
    public boolean isSuccess() {
        // If result type is success, the operation was successful;
        return this.resultType == ResultType.SUCCESS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return Objects.equals(payload, result.payload) && Objects.equals(errorMessages, result.errorMessages) && resultType == result.resultType && Objects.equals(user, result.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload, errorMessages, resultType, user);
    }

    @Override
    public String toString() {
        return "Result{" +
                "payload=" + payload +
                ", errorMessages=" + errorMessages +
                ", resultType=" + resultType +
                ", user=" + user +
                '}';
    }
}
