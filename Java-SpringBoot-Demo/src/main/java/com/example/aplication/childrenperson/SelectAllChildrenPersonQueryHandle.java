package com.example.aplication.childrenperson;

import com.example.aplication.QueryHandleInterface;
import com.example.domain.childrenperson.ChildrenPerson;
import com.example.infraestructure.database.childrenperson.ChildrenPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SelectAllChildrenPersonQueryHandle implements QueryHandleInterface<ChildrenPersonQuery, List<ChildrenPerson>> {

    @Autowired
    private ChildrenPersonRepository repository;


    @Override
    public List<ChildrenPerson> handle(ChildrenPersonQuery query) {

        return this.repository.consultarHijosPersonas();

    }
}
