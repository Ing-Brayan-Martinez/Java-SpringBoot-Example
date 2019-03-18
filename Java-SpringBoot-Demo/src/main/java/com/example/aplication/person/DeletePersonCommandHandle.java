package com.example.aplication.person;

import com.example.aplication.CommandHandleInterface;
import com.example.infraestructure.database.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeletePersonCommandHandle implements CommandHandleInterface<String> {

    @Autowired
    private PersonRepository repository;


    @Override
    public void handle(String command) {

        final int key = Integer.valueOf(command);
        this.repository.delete(key);

    }
}
