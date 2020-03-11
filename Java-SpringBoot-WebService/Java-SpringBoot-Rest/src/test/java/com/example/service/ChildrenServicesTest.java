package com.example.service;

import com.example.service.dto.ChildrenDTO;
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
public class ChildrenServicesTest {

    @Autowired
    private ChildrenService childrenService;

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
        final ChildrenDTO person = new ChildrenDTO();
        person.setNombre("Hetor");
        person.setApellido("Acuña");
        person.setFechaNacimiento(Date.valueOf(LocalDate.of(1990,12,9)));
        person.setTipoSangre("O+");
        person.setPersonId(1L);

        final Optional<ChildrenDTO> pr = this.childrenService.save(person);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void update() {
        final Optional<ChildrenDTO> optional = this.childrenService.findById(2L);
        Assert.isTrue(optional.isPresent(), "Has fracasado la prueba");

        final ChildrenDTO user = optional.get();
        user.setNombre("Chiky");
        user.setApellido("NG");

        final Optional<ChildrenDTO> pr = this.childrenService.update(user);
        Assert.isTrue(pr != null, "Has fracasado la prueba");
    }

    @Test
    public void findById() {
        final Optional<ChildrenDTO> pr = this.childrenService.findById(1L);
        Assert.isTrue(pr.isPresent(), "Has fracasado la prueba");

    }

    @Test
    public void findAll() {
        final List<ChildrenDTO> pr = this.childrenService.findAll();
        Assert.isTrue(!pr.isEmpty(), "Has fracasado la prueba");

    }
}
