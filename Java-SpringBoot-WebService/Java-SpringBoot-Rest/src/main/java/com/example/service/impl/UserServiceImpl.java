package com.example.service.impl;


import com.example.convert.UserConvert;
import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserAuditableService;
import com.example.service.UserService;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserAuditableService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert userConvert;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.findByUserName(username)
            .map(entity -> this.userConvert.toDTO(entity))
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));
    }

    @Override
    public Optional<UserDTO> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        final UserDTO u = (UserDTO) authentication.getPrincipal();

        return Optional.of(u);
    }

    @Override
    public Optional<UserDTO> save(UserDTO dto) {
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        return Optional.ofNullable(this.userRepository.insert(this.userConvert.fromDTO(dto)))
                .map(entity -> this.userConvert.toDTO(entity));
    }

    @Override
    public Optional<UserDTO> update(UserDTO dto) {
        final Optional<User> user = this.userRepository.findById(dto.getUserId());
        return user
                .map(entity -> this.userRepository.update(this.userConvert.fromDTO(entity, dto)))
                .map(entity -> this.userConvert.toDTO(entity));
    }

    @Override
    public Optional<UserDTO> delete(Long id) {
        final Optional<User> optional = this.userRepository.findById(id);
        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                return entity;
            })
            .map(entity -> this.userRepository.update(entity))
            .map(result -> this.userConvert.toDTO(result));
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.userRepository.findById(id)
                .map(entity -> this.userConvert.toDTO(entity));
    }

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream()
                .map(entity -> this.userConvert.toDTO(entity))
                .collect(Collectors.toList());
    }

}
