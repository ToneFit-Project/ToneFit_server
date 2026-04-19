package com.example.tonefitserver.core.exception;

import com.example.tonefitserver.core.dto.ApiResponse;
import com.example.tonefitserver.core.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TonefitException.class)
    public ResponseEntity<ApiResponse<?>> handleTonefitException(TonefitException e) {
        ErrorType errorType = e.getErrorType();
        log.error("Business Exception: {}, {}", errorType.getCode(), errorType.getMessage());
        return ResponseEntity
                .status(errorType.getStatus())
                .body(ApiResponse.error(errorType.getCode(), errorType.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        
        log.error("Validation Exception: {}", message);
        return ResponseEntity
                .status(ErrorType.INVALID_REQUEST.getStatus())
                .body(ApiResponse.error(ErrorType.INVALID_REQUEST.getCode(), message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Internal Server Error: ", e);
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        return ResponseEntity
                .status(errorType.getStatus())
                .body(ApiResponse.error(errorType.getCode(), errorType.getMessage()));
    }
}
