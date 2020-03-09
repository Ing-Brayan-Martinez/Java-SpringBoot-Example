package com.example.controller;

import com.example.service.UserService;
import com.example.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public final class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO dto) {
        this.logger.debug("POST /api/user");

        return this.userService.save(dto)
                .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDTO> update(@PathVariable Long userId, @RequestBody UserDTO dto) {
        this.logger.debug("PUT /api/user/{userId}");
        dto.setUserId(userId);

        return this.userService.update(dto)
                .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        this.logger.debug("DELETE /api/user/{userId}");
        this.userService.delete(userId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long userId) {
        this.logger.debug("GET /api/user/{userId}");

        return this.userService.findById(userId)
                .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAll() {
        this.logger.debug("GET /api/user");

        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }





}
