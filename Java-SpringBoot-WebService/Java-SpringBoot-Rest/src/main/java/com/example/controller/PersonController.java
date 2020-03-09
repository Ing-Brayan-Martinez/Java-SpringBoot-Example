package com.example.controller;

import com.example.service.PersonService;
import com.example.service.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public final class PersonController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonService personService;


    @PostMapping("/person")
    public ResponseEntity<PersonDTO> add(@RequestBody PersonDTO dto) {
        this.logger.debug("POST /api/v1/person");

        return this.personService.save(dto)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/person/{personId}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long personId, @RequestBody PersonDTO dto) {
        this.logger.debug("PUT /api/v1/person/{personId}");
        dto.setKey(personId);

        return this.personService.update(dto)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/person/{personId}")
    public ResponseEntity<PersonDTO> delete(@PathVariable Long personId) {
        this.logger.debug("DELETE /api/v1/person/{personId}");

        return this.personService.delete(personId)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long personId) {
        this.logger.debug("GET /api/v1/person/{personId}");

        return this.personService.findById(personId)
            .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/person")
    public ResponseEntity<List<PersonDTO>> getAll() {
        this.logger.debug("GET /api/v1/person");

        return new ResponseEntity<>(this.personService.findAll(), HttpStatus.OK);
    }

}
