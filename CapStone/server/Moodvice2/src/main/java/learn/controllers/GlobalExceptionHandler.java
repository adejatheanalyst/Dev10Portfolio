package learn.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleException(NullPointerException ex) {
        if (ex.getMessage().contains("null")) {
            return new ResponseEntity<>("Username is required or nonexistent", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleForeignKeyViolation(DataIntegrityViolationException ex){
        if(ex.getMessage().contains("Duplicate entry")) {
            return new ResponseEntity<>("Duplicate body, cannot add", HttpStatus.BAD_REQUEST);
        }
        if (ex.getMessage().contains("foreign key constraint fails")) {
            return new ResponseEntity<>("The item was not found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
