package com.example.client;

import com.example.domain.Person;
import com.example.util.HttpMethod;
import feign.Headers;

import java.util.List;

@Headers(value = "Accept: application/json")
public interface PersonRestClient {

    static final String CREATE_PERSON = HttpMethod.POST + "/create";
    static final String UPDATE_PERSON = HttpMethod.PUT + "/update/{id}";
    static final String DELETE_PERSON = HttpMethod.DELETE + "/delete/{id}";
    static final String GET_ALL_PERSONS = HttpMethod.GET + "/all";
    static final String GET_ALL_PERSONS_BY_NAME = HttpMethod.GET + "/all/by/name/{name}";
    static final String GET_ALL_PERSONS_BY_FAMILY_NAME_ = HttpMethod.GET + "/all/by/familyname/{name}";

    void createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long id);

    List<Person> getAll();

    List<Person> getByName(String name);

    List<Person> getByFamilyName(String familyName);

}
