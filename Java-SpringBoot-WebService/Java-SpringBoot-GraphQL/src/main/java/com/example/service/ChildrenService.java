package com.example.service;

import com.example.Repository.ChildrenRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildrenService {

    @Autowired
    private ChildrenRepository repository;

    public DataFetcher getChildrenByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Long id = dataFetchingEnvironment.getArgument("id");
            return repository.getOne(id);
        };
    }
}
