package com.hyperskill.exception;

public class PlayerMatchNotFoundException extends RuntimeException {
    public PlayerMatchNotFoundException(String message) {
        super(message);
    }
}
