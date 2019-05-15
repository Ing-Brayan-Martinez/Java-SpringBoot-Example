package com.example.aplication.children;

import com.example.aplication.QueryHandleInterface;
import com.example.domain.children.Children;
import com.example.infraestructure.database.children.ChildrenRepository;
import com.example.infraestructure.otros.ChildrenQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SelectAllChildrenQueryHandle implements QueryHandleInterface<ChildrenQuery, List<Children>> {

    @Autowired
    private ChildrenRepository repository;


    @Override
    public List<Children> handle(ChildrenQuery query) {

        return this.repository.consultarTodoList();

    }
}
