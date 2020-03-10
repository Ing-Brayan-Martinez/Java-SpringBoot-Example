package com.example.service;

import com.example.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @BeforeEach
    public void prepared() {
        final UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken("system", "system");
        final Authentication auth = manager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void insert() {
        final UserDTO user = new UserDTO();
        user.setName("Hetor Acuña");
        user.setUsername("hacuña21");
        user.setEmail("hacuña21@gmail.com");
        user.setCommission(9.9F);
        user.setPassword(this.passwordEncoder.encode("12345678"));

        final Optional<UserDTO> pr = this.userService.save(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<UserDTO> optional = this.userService.findById(7L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final UserDTO user = optional.get();
        user.setName("Chiky NG");
        user.setUsername("c.ng");
        user.setEmail("c.ng25@gmail.com");
        user.setCommission(4.0F);


        final Optional<UserDTO> pr = this.userService.update(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<UserDTO> pr = this.userService.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<UserDTO> pr = this.userService.findAll();
        Assert.isTrue(!pr.isEmpty(), "Has fracasado la prueba");

    }
}
