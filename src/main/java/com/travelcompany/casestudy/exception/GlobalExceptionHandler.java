package com.travelcompany.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for handling InvalidAccommodationIdException.
     * Returns a response with a 400 Bad Request status and the exception message.
     *
     * @param ex The InvalidAccommodationIdException to handle.
     * @return ResponseEntity containing the error message and status code.
     */
    @ExceptionHandler(InvalidAccommodationIdException.class)
    public ResponseEntity<String> handleInvalidAccommodationIdException(InvalidAccommodationIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Exception handler for handling AccommodationNotFoundException.
     * Returns a response with a 404 Not Found status and the exception message.
     *
     * @param ex The AccommodationNotFoundException to handle.
     * @return ResponseEntity containing the error message and status code.
     */
    @ExceptionHandler(AccommodationNotFoundException.class)
    public ResponseEntity<String> handleAccommodationNotFoundException(AccommodationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
