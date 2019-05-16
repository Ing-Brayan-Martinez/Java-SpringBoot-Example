package com.example.infraestructure.http;

import com.example.aplication.person.*;
import com.example.domain.person.Person;
import com.example.infraestructure.util.Intent;
import com.example.infraestructure.util.Util;
import com.example.infraestructure.util.Valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private InsertPersonCommandHandle create;

    @Autowired
    private UpdatePersonCommandHandle update;

    @Autowired
    private DeletePersonCommandHandle delete;

    @Autowired
    private SelectAllPersonQueryHandle all;

    @Autowired
    private SelectPersonQueryHandle byID;

    @PostMapping("/create")
    public void insertAction(
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "cedula") String cedula,
        @RequestParam(defaultValue = "altura") Float altura,
        @RequestParam(defaultValue = "telefono") String telefono,
        @RequestParam(defaultValue = "correo") String correo
    ) {

        final Intent command = new Intent();
        command.putExtra(Person.KEY, Valores.CERO);
        command.putExtra(Person.NOMBRE, nombre);
        command.putExtra(Person.APELLIDO, apellido);
        command.putExtra(Person.FECHA_NACIMIENTO, Util.toDate(fechaNacimiento));
        command.putExtra(Person.CEDULA, cedula);
        command.putExtra(Person.ALTURA, altura);
        command.putExtra(Person.TELEFONO, telefono);
        command.putExtra(Person.CORREO, correo);
        command.putExtra(Person.FECHA_REGISTRO, Util.geDate());
        command.putExtra(Person.FECHA_MODIFICACION, Util.geDate());

        create.handle(command);
    }

    @PutMapping("/update")
    public void updateAction(
        @RequestParam(defaultValue = "key") Long key,
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "cedula") String cedula,
        @RequestParam(defaultValue = "altura") Float altura,
        @RequestParam(defaultValue = "telefono") String telefono,
        @RequestParam(defaultValue = "correo") String correo
    ) {

        final Intent command = new Intent();
        command.putExtra(Person.KEY, Valores.CERO);
        command.putExtra(Person.NOMBRE, nombre);
        command.putExtra(Person.APELLIDO, apellido);
        command.putExtra(Person.FECHA_NACIMIENTO, Util.toDate(fechaNacimiento));
        command.putExtra(Person.CEDULA, cedula);
        command.putExtra(Person.ALTURA, altura);
        command.putExtra(Person.TELEFONO, telefono);
        command.putExtra(Person.CORREO, correo);
        command.putExtra(Person.FECHA_REGISTRO, Util.geDate());
        command.putExtra(Person.FECHA_MODIFICACION, Util.geDate());

        update.handle(command);
    }

    @DeleteMapping("/delete")
    public void deleteAction(@RequestParam(defaultValue = "key") Long key) {
        delete.handle(key);
    }

    @GetMapping("/all")
    public List<Person> selectAllAction() {
        return all.handle(Valores.CERO);
    }

    @GetMapping("/all/by/id/{id}")
    public Person selectAction(@RequestParam(defaultValue = "key") Long key) {
        return byID.handle(key);
    }

}
