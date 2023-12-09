package com.travelcompany.casestudy.exception;

/**
 * Custom exception class for handling cases where an accommodation is not found.
 */
public class AccommodationNotFoundException extends RuntimeException {

    /**
     * Constructor to create an instance of the exception with a custom message.
     *
     * @param message A message describing the reason for the exception.
     */
    public AccommodationNotFoundException(String message) {
        super(message);
    }
}
