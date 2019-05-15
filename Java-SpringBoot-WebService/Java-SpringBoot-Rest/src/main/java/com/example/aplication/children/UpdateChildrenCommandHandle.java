
package com.example.aplication.children;

import com.example.aplication.CommandHandleInterface;
import com.example.domain.children.Children;
import com.example.infraestructure.database.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class UpdateChildrenCommandHandle implements CommandHandleInterface<Children> {

    @Autowired
    private ChildrenRepository repository;

    @Override
    public void handle(Children data) {
        this.repository.actualizar(data);
    }
}
