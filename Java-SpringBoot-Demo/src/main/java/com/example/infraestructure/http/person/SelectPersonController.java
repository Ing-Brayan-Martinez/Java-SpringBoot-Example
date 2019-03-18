

package com.example.infraestructure.http.person;

import com.example.aplication.person.PersonQuery;
import com.example.aplication.person.SelectPersonQueryHandle;
import com.example.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
@RequestMapping("/person")
public class SelectPersonController {

    @Autowired
    private SelectPersonQueryHandle commandBus;


    @RequestMapping("/select")
    public Person selectAction(@RequestParam(defaultValue = "key") String key) {

        final PersonQuery query = new PersonQuery();
        query.setId(key);

        return this.commandBus.handle(query);
    }

}
