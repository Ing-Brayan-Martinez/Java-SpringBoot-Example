package com.example.repository;


import com.example.domain.Person;
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
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personReposytory;

    @Test
    public void insert() {
        final Person person = new Person();
        person.setNombre("Brayan");
        person.setApellido("Martinez");
        person.setFechaNacimiento(Date.valueOf(LocalDate.of(1994,12,6)));
        person.setCedula("24456789");
        person.setAltura(1.30F);
        person.setTelefono("02148523652");
        person.setCorreo("brayan@gmail.com");
        person.setCreatedBy(1L);
        person.setUpdatedBy(1L);

        final Person pr = this.personReposytory.insert(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<Person> optional = this.personReposytory.findById(1L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final Person person = optional.get();
        person.setNombre("Jesus");
        person.setApellido("Santander");
        person.setCorreo("jsantander@gmail.com");
        person.setUpdated(new Timestamp(System.currentTimeMillis()));
        person.setUpdatedBy(1L);

        final Person pr = this.personReposytory.update(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<Person> pr = this.personReposytory.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<Person> list = this.personReposytory.findAll();
        Assert.isTrue(!list.isEmpty(), "Has fracasado la prueba");
        list.forEach(System.out::println);
    }
}
