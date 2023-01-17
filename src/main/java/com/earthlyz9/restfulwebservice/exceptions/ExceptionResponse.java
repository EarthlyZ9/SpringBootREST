package com.earthlyz9.restfulwebservice.exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

  private Integer code;
  private String message;
  private String details;
  private LocalDateTime timestamp;
}
