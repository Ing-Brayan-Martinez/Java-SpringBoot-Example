package com.example.aplication.children;

import com.example.aplication.CommandHandleInterface;
import com.example.infraestructure.database.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteChildrenCommandHandle implements CommandHandleInterface<String> {

    @Autowired
    private ChildrenRepository repository;


    @Override
    public void handle(String command) {

        final int key = Integer.valueOf(command);
        this.repository.eliminar(key);

    }
}
