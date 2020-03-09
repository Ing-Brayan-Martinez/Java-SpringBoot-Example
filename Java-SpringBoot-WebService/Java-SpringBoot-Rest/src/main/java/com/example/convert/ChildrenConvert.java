package com.example.convert;

import com.example.domain.Children;
import com.example.service.dto.ChildrenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public final class ChildrenConvert implements Convert<ChildrenDTO, Children> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Children fromDTO(ChildrenDTO dto) {
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

    @Override
    public Children fromDTO(Children entity, ChildrenDTO dto) {
        return null;
    }

    @Override
    public ChildrenDTO toDTO(Children entity) {
        return null;
    }
}
