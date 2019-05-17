package com.example.infraestructure.http;

import com.example.aplication.person.*;
import com.example.domain.person.Person;
import com.example.infraestructure.util.Valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/v1/person")
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
    public void insertAction(@RequestBody Person person) {
        create.handle(person);
    }

    @PutMapping("/update")
    public void updateAction(@RequestBody Person person) {
        update.handle(person);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAction(@PathVariable(value = "id") Long id) {
        delete.handle(id);
    }

    @GetMapping("/all")
    public List<Person> selectAllAction() {
        return all.handle(Valores.CERO);
    }

    @GetMapping("/all/by/id/{id}")
    public Person selectAction(@PathVariable(value = "id") Long id) {
        return byID.handle(id);
    }

}
