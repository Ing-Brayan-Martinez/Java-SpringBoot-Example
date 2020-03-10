package com.example.convert;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.RoleRepository;
import com.example.service.UserAuditableService;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserConvert implements Convert<UserDTO, User> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public User fromDTO(UserDTO userDTO) {

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        final User entity = new User();
        entity.setUserId(userDTO.getUserId());
        entity.setName(userDTO.getName());
        entity.setUsername(userDTO.getUsername());
        entity.setEmail(userDTO.getEmail());
        entity.setCommission(userDTO.getCommission());
        entity.setPassword(userDTO.getPassword());
        entity.setIsAccountNonExpired(userDTO.getIsAccountNonExpired());
        entity.setIsAccountNonLocked(userDTO.getIsAccountNonLocked());
        entity.setIsCredentialsNonExpired(userDTO.getIsCredentialsNonExpired());

        entity.setCreatedBy(audit.getUserId());
        entity.setUpdatedBy(audit.getUserId());

        return entity;
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
