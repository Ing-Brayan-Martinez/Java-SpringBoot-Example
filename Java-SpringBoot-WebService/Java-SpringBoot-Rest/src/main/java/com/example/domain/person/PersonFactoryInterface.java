package com.example.domain.person;

import com.example.infraestructure.otros.PersonCommand;

public interface PersonFactoryInterface {

    public Person toPerson(PersonCommand command);

}
