package com.gw.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloWorldController {
    @RequestMapping("/hello")
    private String hello(){
        return "Welcome to MuseMatch";
    }
}
