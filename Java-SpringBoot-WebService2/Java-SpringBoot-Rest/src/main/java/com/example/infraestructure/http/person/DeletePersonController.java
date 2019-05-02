

package com.example.infraestructure.http.person;

import com.example.aplication.person.DeletePersonCommandHandle;
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
public class DeletePersonController {

    @Autowired
    private DeletePersonCommandHandle commandBus;


    @RequestMapping("/delete")
    public void deleteAction(@RequestParam(defaultValue = "key") String key) {

        this.commandBus.handle(key);

    }


}
