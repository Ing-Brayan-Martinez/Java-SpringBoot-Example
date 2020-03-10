package com.example.repository;

import com.example.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    Person insert(Person data);

    Person update(Person data);

    Optional<Person> findById(long key);

    List<Person> findAll();
}
