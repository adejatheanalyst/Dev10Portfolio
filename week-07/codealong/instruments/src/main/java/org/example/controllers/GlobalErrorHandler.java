package org.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;



@ControllerAdvice // when there is an error this is where we get the informtaion
public class GlobalErrorHandler {

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<List<String>> handleBadGrammer(BadSqlGrammarException ex){
        logError(ex);
        return new ResponseEntity<>(List.of("There was a problem communicating with our database"), HttpStatus.INTERNAL_SERVER_ERROR); //
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<String>> handleAnyExeception(Exception ex){
        return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<String>> handleMessageNotReadable( HttpMessageNotReadableException ex){
        logError(ex);
        return new ResponseEntity<>(List.of("Invalid instrumentType"), HttpStatus.BAD_REQUEST);
    }

    private void logError(Exception ex ) {
        ex.printStackTrace();
    }
}
