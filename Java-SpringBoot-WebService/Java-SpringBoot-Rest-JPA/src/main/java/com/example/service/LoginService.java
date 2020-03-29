package com.example.service;


import com.example.service.dto.UserDTO;

public interface LoginService {

    UserDTO login(String username, String password);
    Boolean logout(String token);
    Boolean isValidToken(String token);
    String createNewToken(String token);

}
