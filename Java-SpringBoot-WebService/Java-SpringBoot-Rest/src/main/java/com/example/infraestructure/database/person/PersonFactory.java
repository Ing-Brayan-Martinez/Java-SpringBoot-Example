package com.example.infraestructure.database.person;

import com.example.infraestructure.otros.PersonCommand;
import com.example.domain.person.Person;
import com.example.domain.person.PersonFactoryInterface;
import com.example.infraestructure.util.Valores;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class PersonFactory implements PersonFactoryInterface {

    private final Logger log = Logger.getLogger(PersonFactory.class);

    @Override
    public Person toPerson(PersonCommand command) {
        Person person = null;

        try {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            final Date fechaNacimiento2   = new Date(format.parse(command.getFechaNacimiento()).getTime());

            person = new Person(
                Valores.CERO,
                command.getNombre(),
                command.getApellido(),
                fechaNacimiento2,
                command.getCedula(),
                Float.valueOf(command.getAltura()),
                command.getTelefono(),
                command.getCorreo(),
                command.getFechaRegistro(),
                command.getFechaModificacion()
            );

        } catch (ParseException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha construido una persona.");
        }

        return person;
    }

}
