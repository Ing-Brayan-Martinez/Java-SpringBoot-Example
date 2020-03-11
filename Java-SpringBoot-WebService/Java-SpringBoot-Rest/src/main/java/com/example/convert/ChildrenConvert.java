package com.example.convert;

import com.example.domain.Children;
import com.example.service.UserAuditableService;
import com.example.service.dto.ChildrenDTO;
import com.example.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public final class ChildrenConvert implements Convert<ChildrenDTO, Children> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public Children fromDTO(ChildrenDTO dto) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        final Children entity = new Children();
        entity.setChildrenId(dto.getChildrenId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTipoSangre(dto.getTipoSangre());
        entity.setPersonId(dto.getPersonId());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
    }

    @Override
    public Children fromDTO(Children entity, ChildrenDTO dto) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        entity.setChildrenId(dto.getChildrenId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTipoSangre(dto.getTipoSangre());
        entity.setPersonId(dto.getPersonId());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
    }

    @Override
    public ChildrenDTO toDTO(Children entity) {

        final ChildrenDTO children = new ChildrenDTO();
        children.setChildrenId(entity.getChildrenId());
        children.setNombre(entity.getNombre());
        children.setApellido(entity.getApellido());
        children.setFechaNacimiento(entity.getFechaNacimiento());
        children.setTipoSangre(entity.getTipoSangre());
        children.setPersonId(entity.getPersonId());

        return children;
    }
}
