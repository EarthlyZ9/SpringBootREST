package com.earthlyz9.restfulwebservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user with the provided id does not exist");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
