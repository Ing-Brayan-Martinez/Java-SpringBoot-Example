package com.example.aplication.person;

import com.example.aplication.QueryHandleInterface;
import com.example.domain.person.Person;
import com.example.infraestructure.database.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SelectAllPersonQueryHandle implements QueryHandleInterface<PersonQuery, List<Person>> {

    @Autowired
    private PersonRepository repository;


    @Override
    public List<Person> handle(PersonQuery query) {

        return this.repository.selectAll();

    }
}
