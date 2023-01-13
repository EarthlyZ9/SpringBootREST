package com.earthlyz9.restfulwebservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Integer code;
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
