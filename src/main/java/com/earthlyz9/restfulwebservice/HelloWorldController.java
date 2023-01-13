package com.earthlyz9.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // @RequestMapping(method=RequestMethod.GET, path="/")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    // GET
    // @RequestMapping(method=RequestMethod.GET, path="/")
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
}

// 단순히 String 형태가 아닌 Bean 으로 반환할 경우 json 으로 반환할 수 있음