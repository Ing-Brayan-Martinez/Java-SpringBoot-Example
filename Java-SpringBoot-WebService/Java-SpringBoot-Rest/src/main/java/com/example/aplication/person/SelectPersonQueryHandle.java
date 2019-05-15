package com.example.aplication.person;

import com.example.aplication.QueryHandleInterface;
import com.example.domain.person.Person;
import com.example.infraestructure.database.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SelectPersonQueryHandle implements QueryHandleInterface<Long, Person> {

    @Autowired
    private PersonRepository repository;

    @Override
    public Person handle(Long key) {
        return this.repository.select(key);
    }

}
