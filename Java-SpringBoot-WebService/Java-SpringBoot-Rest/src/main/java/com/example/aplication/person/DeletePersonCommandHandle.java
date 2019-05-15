package com.example.aplication.person;

import com.example.aplication.CommandHandleInterface;
import com.example.infraestructure.database.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeletePersonCommandHandle implements CommandHandleInterface<Long> {

    @Autowired
    private PersonRepository repository;

    @Override
    public void handle(Long key) {
        this.repository.delete(key);
    }
}
