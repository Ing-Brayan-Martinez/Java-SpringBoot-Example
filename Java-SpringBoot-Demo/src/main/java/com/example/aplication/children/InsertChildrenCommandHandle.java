
package com.example.aplication.children;

import com.example.aplication.CommandHandleInterface;
import com.example.domain.children.Children;

import com.example.infraestructure.database.children.ChildrenFactory;
import com.example.infraestructure.database.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class InsertChildrenCommandHandle implements CommandHandleInterface<ChildrenCommand> {

    @Autowired
    private ChildrenFactory factory;

    @Autowired
    private ChildrenRepository repository;


    @Override
    public void handle(ChildrenCommand data) {
        
        final Children temp = this.factory.toChildren(data);

        this.repository.insertar(temp);
    }
}
