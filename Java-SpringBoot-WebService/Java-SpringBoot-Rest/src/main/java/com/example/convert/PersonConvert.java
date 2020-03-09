package com.example.convert;

import com.example.domain.Person;
import com.example.service.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public final class PersonConvert implements Convert<PersonDTO, Person> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Person fromDTO(PersonDTO dto) {
        return Person.builder()
            .key(dto.getKey())
            .nombre(dto.getNombre())
            .apellido(dto.getApellido())
            .fechaNacimiento(dto.getFechaNacimiento())
            .cedula(dto.getCedula())
            .altura(dto.getAltura())
            .telefono(dto.getTelefono())
            .correo(dto.getCorreo())
            .fechaRegistro(dto.getFechaRegistro())
            .fechaModificacion(dto.getFechaModificacion())
            .build();
    }

    @Override
    public Person fromDTO(Person entity, PersonDTO dto) {
        return null;
    }

    @Override
    public PersonDTO toDTO(Person entity) {
        return null;
    }
}
