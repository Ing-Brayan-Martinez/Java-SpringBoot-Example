package com.example.repository;


import com.example.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void insert() {
        final Role role = new Role();
        role.setName("ROLE_TEST");
        role.setDescription("Test role");
        role.setCreatedBy(1L);
        role.setUpdatedBy(1L);

        final Role pr = this.roleRepository.insert(role);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<Role> optional = this.roleRepository.findById(3L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final Role role = optional.get();
        role.setName("ROLE_FAKE");
        role.setDescription("Update role into Test");
        role.setUpdated(new Timestamp(System.currentTimeMillis()));
        role.setUpdatedBy(1L);

        final Role pr = this.roleRepository.update(role);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<Role> pr = this.roleRepository.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<Role> list = this.roleRepository.findAll();
        Assert.isTrue(!list.isEmpty(), "Has fracasado la prueba");
        list.forEach(System.out::println);
    }

    @Test
    public void findByUserId() {
        final List<Role> list = this.roleRepository.findByUserId(1L);
        Assert.isTrue(!list.isEmpty(), "Has fracasado la prueba");
        list.forEach(System.out::println);
    }
}
