package com.example.service;

import com.example.Repository.PersonRepository;
import com.example.domain.Person;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public DataFetcher getPersonByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
            Person person = repository.getOne(id);
            Map<String, Object> map = person.toMap();
            return map;
        };
    }
}
