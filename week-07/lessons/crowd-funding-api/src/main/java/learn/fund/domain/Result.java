package learn.fund.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Result<T> {

    private ResultType type = ResultType.SUCCESS;
    private T payload;
    private ArrayList<String> messages = new ArrayList<>();

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
        this.type = ResultType.INVALID;
    }

    public void setNotFound() {
        this.type = ResultType.NOT_FOUND;
    }

    public ResultType getType() {
        return type;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result<?> result = (Result<?>) o;

        if (type != result.type) return false;
        if (!Objects.equals(payload, result.payload)) return false;
        return Objects.equals(messages, result.messages);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }
}