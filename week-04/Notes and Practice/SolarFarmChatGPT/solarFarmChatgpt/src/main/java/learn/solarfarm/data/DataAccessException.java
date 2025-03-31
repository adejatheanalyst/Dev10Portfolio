package learn.solarfarm.data;

import java.io.IOException;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, IOException ex) {
        super(message);
    }
}
