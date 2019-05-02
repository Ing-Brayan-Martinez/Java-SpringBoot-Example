
package com.example.infraestructure.http.home;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
public class HomeController {
    
    @RequestMapping("/")
    public String hola() {
        return "<h1>Prueba desde Spring Framework...<h1>";
    }
           
}
