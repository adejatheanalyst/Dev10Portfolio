package learn.field_agent.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<List<String>> handleBadGrammar(BadSqlGrammarException ex) {
        logError(ex);
        return new ResponseEntity<>(List.of("There was a problem communicating with our database."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<List<String>> handleDataAccessApiError(InvalidDataAccessApiUsageException ex){
        logError(ex);
        return new ResponseEntity<>(List.of("There was a problem communicating with our database."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<List<String>> handleDataIntegrityAccessError(DataIntegrityViolationException ex){
        logError(ex);
        return new ResponseEntity<>(List.of("There was a problem communicating with our database."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception ex) {
        ex.printStackTrace();
    }
}
