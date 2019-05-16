
package com.example.aplication.person;

import com.example.aplication.CommandHandleInterface;
import com.example.domain.person.Person;
import com.example.infraestructure.convert.person.PersonConverted;
import com.example.infraestructure.database.person.PersonRepository;
import com.example.infraestructure.mail.EMail;
import com.example.infraestructure.mail.EmailManager;
import com.example.infraestructure.util.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InsertPersonCommandHandle implements CommandHandleInterface<Intent> {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonConverted converted;

    @Autowired
    private EmailManager manager;

    @Override
    public void handle(Intent intent) {
        Person person = this.converted.onConvert(intent);
        repository.insert(person);

        EMail msg = new EMail("hola", "brayanmartinez827@gmail.com", "Este es un correo de prueba");
        manager.sendMessage(msg);
    }

}
