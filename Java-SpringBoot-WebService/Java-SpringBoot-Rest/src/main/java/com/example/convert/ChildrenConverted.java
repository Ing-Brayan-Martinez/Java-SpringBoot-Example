package com.example.convert;

import com.example.domain.Children;
import com.example.service.dto.ChildrenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ChildrenConverted implements Converted<Children, ChildrenDTO> {

    private final static Logger log = LoggerFactory.getLogger(ChildrenConverted.class);

    @Override
    public Children fromDto(ChildrenDTO dto) {
        return Children.builder()
            .key(dto.getKey())
            .nombre(dto.getNombre())
            .apellido(dto.getApellido())
            .fechaNacimiento(dto.getFechaNacimiento())
            .tipoSangre(dto.getTipoSangre())
            .documentoIdentidad(dto.getDocumentoIdentidad())
            .keyPersona(dto.getKeyPersona())
            .fechaRegistro(dto.getFechaRegistro())
            .fechaModificacion(dto.getFechaModificacion())
            .build();
    }
}
