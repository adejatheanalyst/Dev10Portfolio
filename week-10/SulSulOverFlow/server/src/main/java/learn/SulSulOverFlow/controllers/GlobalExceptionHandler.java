package learn.SulSulOverFlow.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleForeignKeyViolation(DataIntegrityViolationException ex){
        if (ex.getMessage().contains("foreign key constraint fails")) {
            return new ResponseEntity<>("The userID provided does not exist.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleException(NullPointerException ex) {
        if(ex.getMessage().contains("null")) {
            return new ResponseEntity<>("The userID provided does not exist.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
