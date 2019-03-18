

package com.example.infraestructure.http.person;

import com.example.aplication.person.PersonQuery;
import com.example.aplication.person.SelectAllPersonQueryHandle;
import com.example.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
@RequestMapping("/person")
public class SelectAllPersonController {

    @Autowired
    private SelectAllPersonQueryHandle commandBus;


    @RequestMapping("/selectall")
    public List<Person> selectAllAction() {

        return this.commandBus.handle(PersonQuery.empty());

    }


}
