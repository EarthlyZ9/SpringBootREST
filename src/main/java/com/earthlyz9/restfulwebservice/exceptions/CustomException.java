package com.earthlyz9.restfulwebservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

  final CustomErrorCode errorCode;
}
