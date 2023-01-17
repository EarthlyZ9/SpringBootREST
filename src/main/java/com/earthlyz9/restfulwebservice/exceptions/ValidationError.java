package com.earthlyz9.restfulwebservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationError extends RuntimeException {

  public ValidationError() {
    super("Validation error");
  }

  public ValidationError(String message) {
    super(message);
  }
}
