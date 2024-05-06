package com.booleanuk.api.cinema.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello { // Giving a descriptive name to the controller

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
