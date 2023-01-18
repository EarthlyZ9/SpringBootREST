package com.earthlyz9.restfulwebservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
  INSTANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
  NO_PERMISSION(HttpStatus.FORBIDDEN, "Not authorized for the resource"),
  AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "Authentication needed"),
  DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "Duplicate resource already exists"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again");


  private final HttpStatus httpStatus;
  private final String message;
}
