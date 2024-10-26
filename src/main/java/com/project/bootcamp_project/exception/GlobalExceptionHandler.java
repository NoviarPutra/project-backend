package com.project.bootcamp_project.exception;

import com.project.bootcamp_project.handler.ApiResponseHandler;
import com.project.bootcamp_project.util.Console;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                  HttpServletRequest request) {
        Map<String, String> errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Console.Error("Error Validasi Data");
        return ApiResponseHandler.buildResponse(
                "DATA TIDAK VALID",
                HttpStatus.BAD_REQUEST,
                errors,
                request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        Console.Error("Error Validasi Method: " + ex.getMessage());
        return ApiResponseHandler.buildResponse(
                "VALIDASI METHOD GAGAL",
                HttpStatus.BAD_REQUEST,
                errors,
                request);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
                                                                         HttpServletRequest request) {
        Map<String, String> errorsDetails = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorsDetails.put("parameter", errorMessage);
        });

        Console.Error("Error Validasi Data");
        return ApiResponseHandler.buildResponse(
                "VALIDASI DATA GAGAL",
                HttpStatus.BAD_REQUEST,
                errorsDetails,
                request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                                HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        String parameterName = ex.getParameterName();
        String errorMessage = "Parameter '" + parameterName + "' is required.";

        errors.put("error", errorMessage);

        Console.Error("Missing Request Parameter: " + parameterName);
        return ApiResponseHandler.buildResponse(
                "PARAMETER HILANG",
                HttpStatus.BAD_REQUEST,
                errors,
                request);
    }

    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public ResponseEntity<Object> handleMethodArgumentConversionNotSupported(MethodArgumentConversionNotSupportedException ex,
                                                                             HttpServletRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("parameter", ex.getName());
        errorDetails.put("message", ex.getMessage());

        Console.Error("Error Nilai Parameter: " + ex.getMessage());
        return ApiResponseHandler.buildResponse(
                "KONVERSI DATA GAGAL",
                HttpStatus.BAD_REQUEST,
                errorDetails,
                request);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex, HttpServletRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        Console.Error("ROLE TIDAK DITEMUKAN");
        return ApiResponseHandler.buildResponse(
                "ROLE TIDAK DITEMUKAN",
                HttpStatus.NOT_FOUND,
                errors,
                request);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Signature validation failed: " + ex.getMessage());

        Console.Error("Error Signature: " + ex.getMessage());
        return ApiResponseHandler.buildResponse(
                "VALIDASI TANDA TANGAN GAGAL",
                HttpStatus.FORBIDDEN,
                errors,
                request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "An unexpected null value was encountered.");

        Console.Error("NullPointerException: " + ex.getMessage());
        return ApiResponseHandler.buildResponse(
                "KESALAHAN NILAI NULL",
                HttpStatus.INTERNAL_SERVER_ERROR,
                errors,
                request);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<Object> handleMappingException(MappingException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        String message = ex.getErrorMessages().stream()
                .map(errorMessage -> errorMessage.getCause().getMessage())
                .collect(Collectors.joining(", "));
        errors.put("error", message);

        Console.Error("MappingException: " + ex.getMessage());
        return ApiResponseHandler.buildResponse(
                "MAPPING DATA GAGAL",
                HttpStatus.BAD_REQUEST,
                errors,
                request);
    }

}
