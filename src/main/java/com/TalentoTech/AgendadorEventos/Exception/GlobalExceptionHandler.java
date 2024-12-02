package com.TalentoTech.AgendadorEventos.Exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Manejar excepciones personalizadas (ResourceNotFoundException)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Código 404
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String constraintMessage = extractConstraintMessage(ex);
        return new ResponseEntity<>(constraintMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Metodo genérico para interpretar mensajes de restricciones.
    private String extractConstraintMessage(DataIntegrityViolationException ex) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        if (message.contains("Duplicate entry")) {
            String value = extractValueFromDuplicateEntry(message);
            return "El valor '" + value + "' ya existe en el sistema.";
        }

        if (message.contains("foreign key constraint fails")) {
            return "Violación de clave foránea. Por favor, verifique las relaciones con otras entidades.";
        }

        return "Se ha violado una restricción de integridad.";
    }

    // Metodo para extraer el valor duplicado del mensaje.
    private String extractValueFromDuplicateEntry(String message) {
        // Extraer el valor entre comillas simples en "Duplicate entry 'value'"
        int start = message.indexOf("Duplicate entry '") + 17;
        int end = message.indexOf("'", start);
        return message.substring(start, end);
    }
}
