
package com.example.infraestructure.http.childrenperson;


import com.example.aplication.childrenperson.ChildrenPersonQuery;
import com.example.aplication.childrenperson.SelectAllChildrenPersonQueryHandle;
import com.example.domain.childrenperson.ChildrenPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
@RequestMapping("/childrenperson")
public class SelectChildrenPersonController {


    @Autowired
    private SelectAllChildrenPersonQueryHandle commandBus;


    @RequestMapping("/selectall")
    public List<ChildrenPerson> selectAllAction() {

        return this.commandBus.handle(ChildrenPersonQuery.empty());
    }
    
}
