package com.example;

import com.example.domain.Person;
import com.example.repository.PersonRepository;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class PersonRestClientTest {

    private PersonRepository repository = new PersonRepository();

    @Test
    public void create() {
        final Person person = new Person();
        person.setNombre("Haide");
        person.setApellido("Romero");
        person.setFechaNacimiento(new Date(System.currentTimeMillis()));
        person.setCedula("87654");
        person.setAltura(85.2f);
        person.setTelefono("123456");
        person.setCorreo("hromero@intelix.biz");
        person.setFechaRegistro(new Date(System.currentTimeMillis()));
        person.setFechaModificacion(new Date(System.currentTimeMillis()));

        this.repository.createPerson(person);
    }

    @Test
    public void update() {
        final Person person = new Person();
        person.setKey(9l);
        person.setNombre("Samuel");
        person.setApellido("Acosta");
        person.setFechaNacimiento(new Date(System.currentTimeMillis()));
        person.setCedula("898989");
        person.setAltura(85.2f);
        person.setTelefono("123456");
        person.setCorreo("sacosta@intelix.biz");
        person.setFechaRegistro(new Date(System.currentTimeMillis()));
        person.setFechaModificacion(new Date(System.currentTimeMillis()));

        this.repository.updatePerson(person);
    }

    @Test
    public void delete() {
        this.repository.deletePerson(11l);
    }

    @Test
    public void getById() {
        Person person = this.repository.getById(1l);
        System.out.println(person);
    }

    @Test
    public void getAll() {
        List<Person> result = this.repository.getAll();
        result.forEach(System.out::println);
    }
}
