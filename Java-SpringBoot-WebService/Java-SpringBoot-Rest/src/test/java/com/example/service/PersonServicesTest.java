package com.example.service;

import com.example.service.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonServicesTest {

    @Autowired
    private PersonService personService;

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
        final PersonDTO person = new PersonDTO();
        person.setNombre("Hetor");
        person.setApellido("Acuña");
        person.setFechaNacimiento(Date.valueOf(LocalDate.of(1990,12,9)));
        person.setCedula("02159632");
        person.setAltura(1.30F);
        person.setTelefono("02148523652");
        person.setCorreo("hacuña21@gmail.com");

        final Optional<PersonDTO> pr = this.personService.save(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<PersonDTO> optional = this.personService.findById(2L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final PersonDTO user = optional.get();
        user.setNombre("Chiky");
        user.setApellido("NG");
        user.setCorreo("c.ng25@gmail.com");

        final Optional<PersonDTO> pr = this.personService.update(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<PersonDTO> pr = this.personService.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<PersonDTO> pr = this.personService.findAll();
        Assert.isTrue(!pr.isEmpty(), "Has fracasado la prueba");

    }
}
