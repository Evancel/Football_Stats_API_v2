package com.hyperskill.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(PlayerNotFoundException ex) {
        log.warn("Player not found: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Player not found");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(PlayerAlreadyExistsException ex) {
        log.warn("Player already exists {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Player already exists");
        return ResponseEntity.badRequest().body(error);
    }
}
