
package com.example.infraestructure.http.children;


import com.example.aplication.children.ChildrenQuery;
import com.example.aplication.children.SelectAllChildrenQueryHandle;
import com.example.domain.children.Children;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
@RequestMapping("/children")
public class SelectAllChildrenController {

    @Autowired
    private SelectAllChildrenQueryHandle commandBus;


    @RequestMapping("/selectall")
    public List<Children> selectAllAction() {

        return this.commandBus.handle(ChildrenQuery.empty());

    }


}
