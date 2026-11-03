package com.springsecurity.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public static String testHello(){
        return "Hey Pranay, you are now on secure endpoint.";
    }

}
