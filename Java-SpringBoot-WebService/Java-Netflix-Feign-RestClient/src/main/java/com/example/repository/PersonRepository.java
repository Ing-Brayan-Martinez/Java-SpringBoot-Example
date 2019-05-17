package com.example.repository;

import com.example.client.PersonRestClient;
import com.example.domain.Person;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;

public class PersonRepository implements PersonRestClient {

    private static final String BASE_URL = "http://localhost:8080/v1/person";
    private final PersonRestClient restClient;

    public PersonRepository() {
        this.restClient = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .target(PersonRestClient.class, BASE_URL);
    }

    @Override
    public void createPerson(Person person) {
        this.restClient.createPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        this.restClient.updatePerson(person);
    }

    @Override
    public void deletePerson(Long id) {
        this.restClient.deletePerson(id);
    }

    @Override
    public List<Person> getAll() {
        return this.restClient.getAll();
    }

    @Override
    public Person getById(Long id) {
        return this.restClient.getById(id);
    }

    @Override
    public List<Person> getByName(String name) {
        return null;
    }

    @Override
    public List<Person> getByFamilyName(String familyName) {
        return null;
    }
}
