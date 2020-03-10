package com.example.repository;


import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert() {
        final User user = new User();
        user.setName("Jesus Santander");
        user.setUsername("jesus21");
        user.setEmail("jesus.santander@gmail.com");
        user.setCommission(7.5F);
        user.setPassword(this.passwordEncoder.encode("12345678"));

        user.setCreatedBy(1L);
        user.setUpdatedBy(1L);

        final User pr = this.userRepository.insert(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<User> optional = this.userRepository.findById(6L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final User user = optional.get();
        user.setName("Orlando Labrador");
        user.setUsername("ola21");
        user.setEmail("orlando.labrador@gmail.com");
        user.setCommission(4.0F);
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedBy(1L);

        final User pr = this.userRepository.update(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<User> pr = this.userRepository.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<User> list = this.userRepository.findAll();
        Assert.isTrue(!list.isEmpty(), "Has fracasado la prueba");
        list.forEach(System.out::println);
    }
}
