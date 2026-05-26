package com.bajaj.bfhl.exception;

import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors (e.g., null data array).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BfhlResponse errorResponse = buildErrorResponse();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles all other unexpected exceptions gracefully.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGenericException(Exception ex) {
        BfhlResponse errorResponse = buildErrorResponse();
        return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
    }

    private BfhlResponse buildErrorResponse() {
        return BfhlResponse.builder()
                .isSuccess(false)
                .userId("asmi_gupta_10072004")
                .email("asmigupta230828@acropolis.in")
                .rollNumber("0827IT231034")
                .oddNumbers(Collections.emptyList())
                .evenNumbers(Collections.emptyList())
                .alphabets(Collections.emptyList())
                .specialCharacters(Collections.emptyList())
                .sum("0")
                .concatString("")
                .build();
    }
}
