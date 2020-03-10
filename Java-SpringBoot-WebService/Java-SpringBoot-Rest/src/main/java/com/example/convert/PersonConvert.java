package com.example.convert;

import com.example.domain.Person;
import com.example.service.UserAuditableService;
import com.example.service.dto.PersonDTO;
import com.example.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public final class PersonConvert implements Convert<PersonDTO, Person> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public Person fromDTO(PersonDTO dto) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        final Person entity = new Person();
        entity.setParsonId(dto.getParsonId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setCedula(dto.getCedula());
        entity.setAltura(dto.getAltura());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
    }

    @Override
    public Person fromDTO(Person entity, PersonDTO dto) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        entity.setParsonId(dto.getParsonId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setCedula(dto.getCedula());
        entity.setAltura(dto.getAltura());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
    }

    @Override
    public PersonDTO toDTO(Person entity) {

        final PersonDTO person = new PersonDTO();
        person.setParsonId(entity.getParsonId());
        person.setNombre(entity.getNombre());
        person.setApellido(entity.getApellido());
        person.setFechaNacimiento(entity.getFechaNacimiento());
        person.setCedula(entity.getCedula());
        person.setAltura(entity.getAltura());
        person.setTelefono(entity.getTelefono());
        person.setCorreo(entity.getCorreo());

        return person;
    }
}
