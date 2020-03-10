package com.example.repository;


import com.example.domain.Children;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ChildrenRepositoryTest {

    @Autowired
    private ChildrenReposytory childrenReposytory;

    @Test
    public void insert() {
        final Children person = new Children();
        person.setNombre("Brayan");
        person.setApellido("Martinez");
        person.setFechaNacimiento(Date.valueOf(LocalDate.of(1994,12,6)));
        person.setTipoSangre("ORH+");
        person.setPersonId(1L);
        person.setCreatedBy(1L);
        person.setUpdatedBy(1L);

        final Children pr = this.childrenReposytory.insert(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<Children> optional = this.childrenReposytory.findById(2L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final Children person = optional.get();
        person.setNombre("Jesus");
        person.setApellido("Santander");
        person.setUpdated(new Timestamp(System.currentTimeMillis()));
        person.setUpdatedBy(1L);

        final Children pr = this.childrenReposytory.update(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<Children> pr = this.childrenReposytory.findById(2L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<Children> list = this.childrenReposytory.findAll();
        Assert.isTrue(!list.isEmpty(), "Has fracasado la prueba");
        list.forEach(System.out::println);
    }
}
