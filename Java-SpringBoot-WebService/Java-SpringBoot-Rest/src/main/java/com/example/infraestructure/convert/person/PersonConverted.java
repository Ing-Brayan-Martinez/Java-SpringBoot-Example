package com.example.infraestructure.convert.person;

import com.example.domain.ConvertedInterface;
import com.example.domain.person.Person;
import com.example.infraestructure.util.Intent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
public class PersonConverted implements ConvertedInterface<Person> {

    private final static Logger log = LoggerFactory.getLogger(PersonConverted.class);

    @Override
    public Person onConvert(Intent command) {
        return new Person.Builder()
            .withKey((Long) command.getExtra(Person.KEY))
            .withNombre((String) command.getExtra(Person.NOMBRE))
            .withApellido((String) command.getExtra(Person.APELLIDO))
            .withFechaNacimiento((Date) command.getExtra(Person.FECHA_NACIMIENTO))
            .withCedula((String) command.getExtra(Person.CEDULA))
            .withAltura((Float) command.getExtra(Person.ALTURA))
            .withTelefono((String) command.getExtra(Person.TELEFONO))
            .withCorreo((String) command.getExtra(Person.CORREO))
            .withFechaRegistro((Date) command.getExtra(Person.FECHA_REGISTRO))
            .withFechaModificacion((Date) command.getExtra(Person.FECHA_MODIFICACION))
            .build();
    }

}
