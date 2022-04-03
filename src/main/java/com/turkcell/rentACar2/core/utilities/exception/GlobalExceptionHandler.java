package com.turkcell.rentACar2.core.utilities.exception;

import com.turkcell.rentACar2.core.utilities.results.ErrorDataResult;
import com.turkcell.rentACar2.core.utilities.results.ErrorResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResult> handleGeneralException(
            Exception exception, WebRequest request) {
        return ResponseEntity.internalServerError().body(new ErrorResult("Internal Server Error"));
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<ErrorResult> handleBusinessException(
            BusinessException businessException, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResult(businessException.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(new ErrorDataResult<>(errors, "Validation errors"));
    }
}
