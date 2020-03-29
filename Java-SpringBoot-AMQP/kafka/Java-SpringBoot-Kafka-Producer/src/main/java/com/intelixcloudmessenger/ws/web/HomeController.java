package com.intelixcloudmessenger.ws.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class HomeController {

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.just("Hello from Vert.x!");
    }
}
