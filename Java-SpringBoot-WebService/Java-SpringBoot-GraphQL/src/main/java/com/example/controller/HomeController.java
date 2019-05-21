
package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class HomeController {

    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String hola() {
        return "<h1>Prueba desde Spring Framework...<h1>";
    }
           
}
