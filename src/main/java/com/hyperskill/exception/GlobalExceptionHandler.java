package com.hyperskill.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TeamAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleTeamAlreadyExistsException(TeamAlreadyExistsException exception) {
        log.warn("Team already exists {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Team already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTeamNotFoundException(TeamNotFoundException exception) {
        log.warn("Team not found {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Team not found");
        return ResponseEntity.badRequest().body(errors);
    }
}
