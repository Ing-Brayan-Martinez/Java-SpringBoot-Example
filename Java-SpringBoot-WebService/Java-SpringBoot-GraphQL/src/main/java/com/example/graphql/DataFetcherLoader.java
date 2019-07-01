package com.example.graphql;

import com.example.service.BookService;
import com.example.service.ChildrenService;
import com.example.service.PersonService;
import com.example.util.Loader;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFetcherLoader implements Loader<RuntimeWiring> {

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ChildrenService childrenService;

    @Override
    public RuntimeWiring onLoad() {
        return  RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("bookById", bookService.getBookById()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("personById", personService.getPersonById()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("childrenById", childrenService.getChildrenById()))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("updateNamePerson", personService.updatePerson()))
                .type(TypeRuntimeWiring.newTypeWiring("Book").dataFetcher("author", bookService.getAuthorDataFetcher()))
                .type(TypeRuntimeWiring.newTypeWiring("Person").dataFetcher("childrens", childrenService.getChildrenListByKeyPerson()))
                .build();
    }
}
