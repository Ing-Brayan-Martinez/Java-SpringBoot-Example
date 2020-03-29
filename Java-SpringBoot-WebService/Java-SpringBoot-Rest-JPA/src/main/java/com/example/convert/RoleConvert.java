package com.example.convert;


import com.example.domain.Role;
import com.example.service.dto.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public final class RoleConvert implements Convert<RoleDTO, Role> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Role fromDTO(RoleDTO dto) {
        return this.modelMapper.map(dto, Role.class);
    }

    @Override
    public Role fromDTO(Role entity, RoleDTO dto) {
        this.modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        return this.modelMapper.map(entity, RoleDTO.class);
    }
}
