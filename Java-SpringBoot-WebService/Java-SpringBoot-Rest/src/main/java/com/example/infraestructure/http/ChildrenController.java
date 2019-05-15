package com.example.infraestructure.http;

import com.example.aplication.children.*;
import com.example.domain.children.Children;
import com.example.infraestructure.otros.ChildrenQuery;
import com.example.infraestructure.util.Util;
import com.example.infraestructure.util.Valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/api/v1/children")
public class ChildrenController {

    @Autowired
    private InsertChildrenCommandHandle create;

    @Autowired
    private UpdateChildrenCommandHandle update;

    @Autowired
    private DeleteChildrenCommandHandle delete;

    @Autowired
    private SelectAllChildrenQueryHandle all;

    @Autowired
    private SelectChildrenQueryHandle byID;

    @PostMapping("/create")
    public void insertAction (
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "tipoSangre") String tipoSangre,
        @RequestParam(defaultValue = "documentoIdentidad") String documentoIdentidad,
        @RequestParam(defaultValue = "KeyPersona") Integer KeyPersona
    ) {

        final Children command = new Children();
        command.setKey(Valores.CERO);
        command.setNombre(nombre);
        command.setApellido(apellido);
        command.setFechaNacimiento(Util.toDate(fechaNacimiento));
        command.setTipoSangre(tipoSangre);
        command.setDocumentoIdentidad(documentoIdentidad);
        command.setKeyPersona(KeyPersona);
        command.setFechaRegistro(Util.geDate());
        command.setFechaModificacion(Util.geDate());

        create.handle(command);
    }

    @PutMapping("/update")
    public void updateAction(
        @RequestParam(defaultValue = "key") Integer key,
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "tipoSangre") String tipoSangre,
        @RequestParam(defaultValue = "documentoIdentidad") String documentoIdentidad,
        @RequestParam(defaultValue = "KeyPersona") Integer KeyPersona
    ) {

        final Children command = new Children();
        command.setKey(key);
        command.setNombre(nombre);
        command.setApellido(apellido);
        command.setFechaNacimiento(Util.toDate(fechaNacimiento));
        command.setTipoSangre(tipoSangre);
        command.setDocumentoIdentidad(documentoIdentidad);
        command.setKeyPersona(KeyPersona);
        command.setFechaRegistro(Util.geDate());
        command.setFechaModificacion(Util.geDate());

        update.handle(command);
    }

    @DeleteMapping("/delete")
    public void deleteAction(@RequestParam(defaultValue = "key") Long key) {
        delete.handle(key);
    }

    @GetMapping("/all")
    public List<Children> selectAllAction() {
        return all.handle(ChildrenQuery.empty());
    }

    @GetMapping("/all/by/id/{id}")
    public Children selectAction(@RequestParam(defaultValue = "key") Long key) {
        return byID.handle(key);
    }
}
