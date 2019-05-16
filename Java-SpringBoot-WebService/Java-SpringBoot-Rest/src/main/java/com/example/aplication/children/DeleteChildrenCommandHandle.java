package com.example.aplication.children;

import com.example.aplication.CommandHandleInterface;
import com.example.infraestructure.database.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteChildrenCommandHandle implements CommandHandleInterface<Long> {

    @Autowired
    private ChildrenRepository repository;

    @Override
    public void handle(Long key) {
        this.repository.eliminar(key);
    }
}
