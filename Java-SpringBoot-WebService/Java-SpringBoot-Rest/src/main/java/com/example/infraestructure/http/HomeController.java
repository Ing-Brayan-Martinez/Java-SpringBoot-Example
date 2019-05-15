
package com.example.infraestructure.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String hola() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "<h1>Prueba desde Spring Framework...<h1>";
    }
           
}
