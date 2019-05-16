package com.example.infraestructure.http;

import com.example.aplication.children.*;
import com.example.domain.children.Children;
import com.example.infraestructure.util.Intent;
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

        final Intent command = new Intent();
        command.putExtra(Children.KEY, Valores.CERO);
        command.putExtra(Children.NOMBRE, nombre);
        command.putExtra(Children.APELLIDO, apellido);
        command.putExtra(Children.FECHA_NACIMIENTO, Util.toDate(fechaNacimiento));
        command.putExtra(Children.TIPO_SANGRE, tipoSangre);
        command.putExtra(Children.DOCUMENTO_IDENTIDAD, documentoIdentidad);
        command.putExtra(Children.PERSONA, KeyPersona);
        command.putExtra(Children.FECHA_REGISTRO, Util.geDate());
        command.putExtra(Children.FECHA_MODIFICACION, Util.geDate());

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

        final Intent command = new Intent();
        command.putExtra(Children.KEY, key);
        command.putExtra(Children.NOMBRE, nombre);
        command.putExtra(Children.APELLIDO, apellido);
        command.putExtra(Children.FECHA_NACIMIENTO, Util.toDate(fechaNacimiento));
        command.putExtra(Children.TIPO_SANGRE, tipoSangre);
        command.putExtra(Children.DOCUMENTO_IDENTIDAD, documentoIdentidad);
        command.putExtra(Children.PERSONA, KeyPersona);
        command.putExtra(Children.FECHA_REGISTRO, Util.geDate());
        command.putExtra(Children.FECHA_MODIFICACION, Util.geDate());

        update.handle(command);
    }

    @DeleteMapping("/delete")
    public void deleteAction(@RequestParam(defaultValue = "key") Long key) {
        delete.handle(key);
    }

    @GetMapping("/all")
    public List<Children> selectAllAction() {
        return all.handle(Valores.CERO);
    }

    @GetMapping("/all/by/id/{id}")
    public Children selectAction(@RequestParam(defaultValue = "key") Long key) {
        return byID.handle(key);
    }
}
