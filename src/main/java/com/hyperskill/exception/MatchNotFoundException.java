package com.hyperskill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a match is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchNotFoundException extends RuntimeException {

    /**
     * Constructs a new MatchNotFoundException with the specified detail message.
     * @param message the detail message
     */
    public MatchNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new MatchNotFoundException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public MatchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}