package com.example.service;

import com.example.Repository.ChildrenRepository;
import com.example.domain.Children;
import com.example.domain.Person;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChildrenService {

    @Autowired
    private ChildrenRepository repository;


    public DataFetcher getChildrenById() {
        return dataFetchingEnvironment -> {
            Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
            Children children = repository.getOne(id);
            Map<String, Object> map = children.toMap();
            return map;
        };
    }

    public DataFetcher getChildrenListByKeyPerson() {
        return dataFetchingEnvironment -> {
            Map<String,Object> json = dataFetchingEnvironment.getSource();
            Long keyPerson = (Long) json.get("keyPerson");

            return repository.findAll()
                    .stream()
                    .filter(children -> children.getKeyPersona().getKeyPerson().equals(keyPerson))
                    .map(Children::toMap)
                    .collect(Collectors.toList());
        };
    }
}
