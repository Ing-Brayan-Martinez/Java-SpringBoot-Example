

package com.example.infraestructure.http.person;

import com.example.aplication.person.InsertPersonCommandHandle;
import com.example.aplication.person.PersonCommand;
import com.example.infraestructure.util.Util;
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
public class InsertPersonController {

    @Autowired
    private InsertPersonCommandHandle commandBus;


    @RequestMapping("/insert")
    public void insertAction(
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "cedula") String cedula,
        @RequestParam(defaultValue = "altura") String altura,
        @RequestParam(defaultValue = "telefono") String telefono,
        @RequestParam(defaultValue = "correo") String correo
    ) {

        final PersonCommand command = new PersonCommand(
            "",
            nombre,
            apellido,
            fechaNacimiento,
            cedula,
            altura,
            telefono,
            correo,
            Util.geDate(),
            Util.geDate()
        );

        this.commandBus.handle(command);

    }

}
