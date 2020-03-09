package com.example.controller;

import com.example.service.ChildrenService;
import com.example.service.dto.ChildrenDTO;
import com.example.util.Util;
import com.example.util.Valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public final class ChildrenController {

    @Autowired
    private ChildrenService service;

    @PostMapping("/children")
    public ResponseEntity<ChildrenDTO> insertAction (
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "tipoSangre") String tipoSangre,
        @RequestParam(defaultValue = "documentoIdentidad") String documentoIdentidad,
        @RequestParam(defaultValue = "KeyPersona") Integer KeyPersona
    ) {

        final ChildrenDTO command = new ChildrenDTO();
        command.setKey(Valores.CERO);
        command.setNombre(nombre);
        command.setApellido(apellido);
        command.setFechaNacimiento(Util.toDate(fechaNacimiento));
        command.setTipoSangre(tipoSangre);
        command.setDocumentoIdentidad(documentoIdentidad);
        command.setKeyPersona(KeyPersona);
        command.setFechaRegistro(Util.geDate());
        command.setFechaModificacion(Util.geDate());

        return this.service.save(command)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/children")
    public ResponseEntity<ChildrenDTO> updateAction(
        @RequestParam(defaultValue = "key") Integer key,
        @RequestParam(defaultValue = "nombre") String nombre,
        @RequestParam(defaultValue = "apellido") String apellido,
        @RequestParam(defaultValue = "fechaNacimiento") String fechaNacimiento,
        @RequestParam(defaultValue = "tipoSangre") String tipoSangre,
        @RequestParam(defaultValue = "documentoIdentidad") String documentoIdentidad,
        @RequestParam(defaultValue = "KeyPersona") Integer KeyPersona
    ) {

        final ChildrenDTO command = new ChildrenDTO();
        command.setKey(key);
        command.setNombre(nombre);
        command.setApellido(apellido);
        command.setFechaNacimiento(Util.toDate(fechaNacimiento));
        command.setTipoSangre(tipoSangre);
        command.setDocumentoIdentidad(documentoIdentidad);
        command.setKeyPersona(KeyPersona);
        command.setFechaRegistro(Util.geDate());
        command.setFechaModificacion(Util.geDate());

        return this.service.update(command)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/children")
    public ResponseEntity<ChildrenDTO> deleteAction(@RequestParam(defaultValue = "key") Long key) {
        return this.service.delete(key)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/children")
    public ResponseEntity<List<ChildrenDTO>> selectAllAction() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/children/{id}")
    public ResponseEntity<ChildrenDTO> selectAction(@RequestParam(defaultValue = "key") Long key) {
        return this.service.findById(key)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
