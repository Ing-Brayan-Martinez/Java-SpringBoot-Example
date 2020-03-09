package com.example.convert;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.RoleRepository;
import com.example.service.UserAuditableService;
import com.example.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserConvert implements Convert<UserDTO, User> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public User fromDTO(UserDTO userDTO) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        final User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setCommission(userDTO.getCommission());
        user.setPassword(userDTO.getPassword());
        user.setIsAccountNonExpired(userDTO.getIsAccountNonExpired());
        user.setIsAccountNonLocked(userDTO.getIsAccountNonLocked());
        user.setIsCredentialsNonExpired(userDTO.getIsCredentialsNonExpired());

        user.setCreatedBy(audit.getUserId());
        user.setUpdatedBy(audit.getUserId());

        return user;
    }

    @Override
    public User fromDTO(User entity, UserDTO dto) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setCommission(dto.getCommission());
        entity.setPassword(dto.getPassword());
        entity.setIsAccountNonExpired(dto.getIsAccountNonExpired());
        entity.setIsAccountNonLocked(dto.getIsAccountNonLocked());
        entity.setIsCredentialsNonExpired(dto.getIsCredentialsNonExpired());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
    }

    @Override
    public UserDTO toDTO(User entity) {

        final List<Role> roles = this.roleRepository.findByUserId(entity.getUserId());

        final UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setCommission(entity.getCommission());
        dto.setPassword(entity.getPassword());
        dto.setIsAccountNonExpired(entity.getIsAccountNonExpired());
        dto.setIsAccountNonLocked(entity.getIsAccountNonLocked());
        dto.setIsCredentialsNonExpired(entity.getIsCredentialsNonExpired());
        dto.setRoles(roles);

        return dto;

    }

}
