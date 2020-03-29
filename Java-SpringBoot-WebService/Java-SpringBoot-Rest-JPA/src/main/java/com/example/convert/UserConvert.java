package com.example.convert;


import com.example.domain.User;
import com.example.service.dto.RoleDTO;
import com.example.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class UserConvert implements Convert<UserDTO, User> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User fromDTO(UserDTO dto) {
        return this.modelMapper.map(dto, User.class);
    }

    @Override
    public User fromDTO(User entity, UserDTO dto) {
        this.modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public UserDTO toDTO(User entity) {
        final List<RoleDTO> roles = entity.getRoles()
                .stream()
                .map((role) -> this.modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());

        final UserDTO dto = this.modelMapper.map(entity, UserDTO.class);
    	dto.setRoles(roles);

        return dto;
    }

}
