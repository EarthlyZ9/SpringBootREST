package com.earthlyz9.restfulwebservice.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import javax.security.sasl.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(Exception exception,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.NOT_FOUND.value(),
        "Not found",
        exception.getMessage(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidationError.class)
  public final ResponseEntity<Object> handleValidationErrorException(ValidationError exception,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Validation error",
        exception.getMessage(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex,
      HttpServletResponse response) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.UNAUTHORIZED.value(),
        "Authentication needed",
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<Object> handleCustomException(CustomException exception,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getErrorCode().getHttpStatus().value(),
        exception.getErrorCode().getMessage(),
        exception.getMessage().split(":")[0],
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, exception.getErrorCode().getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Failed to parse JSON",
        ex.getMessage(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Validation error",
        ex.getBindingResult().toString(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
