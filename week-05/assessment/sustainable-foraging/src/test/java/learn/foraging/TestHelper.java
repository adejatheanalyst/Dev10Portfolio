package learn.foraging;

import learn.foraging.domain.Result;

public class TestHelper {
    public static <T> Result<T> makeResult(String message, T payload) {
        Result<T> result = new Result<>();
        if (message != null) {
            result.addErrorMessage(message);
        }
        if (payload != null) {
            result.setPayload(payload);
        }
        return result;
    }
}
