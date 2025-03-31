package learn.solarfarm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Result <T> {
    private T payload;
    private List<String> errorMessages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;

    public Result() {
    }

    public T getPayload() {
        return payload;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String format, Object... args) {
        this.resultType = ResultType.INVALID;
        errorMessages.add(String.format(format, args));
    }

        public boolean isSuccess() {
            // If result type is success, the operation was successful;
            return this.resultType == ResultType.SUCCESS;
        }

    public ResultType getResultType() {
        return resultType;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return Objects.equals(payload, result.payload) && Objects.equals(errorMessages, result.errorMessages) && resultType == result.resultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload, errorMessages, resultType);
    }
}
