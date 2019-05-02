package com.example.aplication.children;

import com.example.aplication.QueryHandleInterface;
import com.example.domain.children.Children;
import com.example.infraestructure.database.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SelectChildrenQueryHandle implements QueryHandleInterface<ChildrenQuery, Children> {

    @Autowired
    private ChildrenRepository repository;


    @Override
    public Children handle(ChildrenQuery query) {

        final int key = Integer.valueOf(query.getId());

        return this.repository.consultar(key);
    }
}
