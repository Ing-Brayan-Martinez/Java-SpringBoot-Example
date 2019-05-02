
package com.example.infraestructure.http.children;


import com.example.aplication.children.ChildrenCommand;
import com.example.aplication.children.UpdateChildrenCommandHandle;
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
@RequestMapping("/children")
public class UpdateChildrenController {

    @Autowired
    private UpdateChildrenCommandHandle commandBus;


    @RequestMapping("/update")
    public void updateAction(
        @RequestParam(defaultValue = "key") String key,
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "tipoSangre") String tipoSangre,
        @RequestParam(defaultValue = "documentoIdentidad") String documentoIdentidad,
        @RequestParam(defaultValue = "KeyPersona") String KeyPersona
    ) {

        final ChildrenCommand command = new ChildrenCommand(
            key,
            nombre,
            apellido,
            fechaNacimiento,
            tipoSangre,
            documentoIdentidad,
            KeyPersona,
            Util.geDate(),
            Util.geDate()
        );

        this.commandBus.handle(command);

    }

}
