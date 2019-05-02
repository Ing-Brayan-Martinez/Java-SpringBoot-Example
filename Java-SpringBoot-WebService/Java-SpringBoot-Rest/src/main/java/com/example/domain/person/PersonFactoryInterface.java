package com.example.domain.person;

import com.example.aplication.person.PersonCommand;

public interface PersonFactoryInterface {

    public Person toPerson(PersonCommand command);

}