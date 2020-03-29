package com.example.controller;


import com.example.service.LoginService;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public final class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody Map<String,String> dto) {
        return this.loginService.login(dto.get("username"), dto.get("password"));
    }

    @PostMapping("/logout")
    public Boolean logout(@RequestParam String token) {
        return this.loginService.logout(token);
    }

    @GetMapping("/login/token/valid")
    public Boolean isValidToken(@RequestParam String token) {
        return this.loginService.isValidToken(token);
    }

    @GetMapping("/login/token")
    public String createNewToken(@RequestParam String token) {
        return this.loginService.createNewToken(token);
    }

}
