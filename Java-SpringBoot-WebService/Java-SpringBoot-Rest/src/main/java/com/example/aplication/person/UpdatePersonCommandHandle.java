
package com.example.aplication.person;

import com.example.aplication.CommandHandleInterface;
import com.example.domain.person.Person;
import com.example.infraestructure.convert.person.PersonConverted;
import com.example.infraestructure.database.person.PersonRepository;
import com.example.infraestructure.util.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdatePersonCommandHandle implements CommandHandleInterface<Intent> {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonConverted converted;

    @Override
    public void handle(Intent intent) {
        Person person = this.converted.onConvert(intent);
        this.repository.update(person);
    }
}
