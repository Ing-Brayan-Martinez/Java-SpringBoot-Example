package com.example.repository;

import com.example.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonReposytory {

    public Person insert(Person data);

    public Person update(Person data);

    public Person delete(long key);

    public Optional<Person> findById(long key);

    public List<Person> findAll();
}
