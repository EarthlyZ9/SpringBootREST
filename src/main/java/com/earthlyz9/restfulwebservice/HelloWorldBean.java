package com.earthlyz9.restfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data annotation - getter, setter 자동으로 추가됨
// AllArgsConstructor - 모든 arg 를 다 가지고 있는 생성자 자동 생성
// NoArgsConstructor - 빈 생성자 생성

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;
}
