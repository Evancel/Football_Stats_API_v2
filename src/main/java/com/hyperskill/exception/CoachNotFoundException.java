package com.hyperskill.exception;

/**
 * Exception thrown when a coach is not found
 */
public class CoachNotFoundException extends RuntimeException {
    public CoachNotFoundException(String message) {
        super(message);
    }
}