package com.travelcompany.casestudy.exception;

/**
 * Custom exception class for invalid accommodation ID.
 * This exception is thrown when an invalid accommodation ID is encountered.
 */
public class InvalidAccommodationIdException extends RuntimeException {

    /**
     * Constructs a new InvalidAccommodationIdException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public InvalidAccommodationIdException(String message) {
        super(message);
    }
}
