
package com.example.aplication.children;

import com.example.aplication.CommandHandleInterface;
import com.example.domain.children.Children;
import com.example.infraestructure.convert.children.ChildrenConverted;
import com.example.infraestructure.database.children.ChildrenRepository;
import com.example.infraestructure.util.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertChildrenCommandHandle implements CommandHandleInterface<Intent> {

    @Autowired
    private ChildrenRepository repository;

    @Autowired
    private ChildrenConverted factory;

    @Override
    public void handle(Intent intent) {
        Children data = this.factory.onConvert(intent);
        this.repository.insertar(data);
    }
}
