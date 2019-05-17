package com.example.client;

import com.example.domain.Person;
import com.example.util.HttpMethod;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers(value = "Accept: application/json")
public interface PersonRestClient {

    static final String CREATE_PERSON = HttpMethod.POST + "/create";
    static final String UPDATE_PERSON = HttpMethod.PUT + "/update";
    static final String DELETE_PERSON = HttpMethod.DELETE + "/delete/{id}";
    static final String GET_ALL_PERSONS = HttpMethod.GET + "/all";
    static final String GET_ALL_PERSONS_BY_ID = HttpMethod.GET + "/all/by/id/{id}";

    static final String GET_ALL_PERSONS_BY_NAME = HttpMethod.GET + "/all/by/name/{name}";
    static final String GET_ALL_PERSONS_BY_FAMILY_NAME_ = HttpMethod.GET + "/all/by/familyname/{name}";

    @Headers("Content-Type: application/json")
    @RequestLine(CREATE_PERSON)
    void createPerson(Person person);

    @Headers("Content-Type: application/json")
    @RequestLine(UPDATE_PERSON)
    void updatePerson(Person person);

    @Headers("Content-Type: application/json")
    @RequestLine(DELETE_PERSON)
    void deletePerson(@Param("id") Long id);

    @Headers("Content-Type: application/json")
    @RequestLine(GET_ALL_PERSONS)
    List<Person> getAll();

    @Headers("Content-Type: application/json")
    @RequestLine(GET_ALL_PERSONS_BY_ID)
    Person getById(@Param("id") Long id);

    @Headers("Content-Type: application/json")
    @RequestLine(GET_ALL_PERSONS_BY_NAME)
    List<Person> getByName(String name);

    @Headers("Content-Type: application/json")
    @RequestLine(GET_ALL_PERSONS_BY_FAMILY_NAME_)
    List<Person> getByFamilyName(String familyName);

}
