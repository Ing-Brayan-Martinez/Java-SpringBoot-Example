package com.example.service.impl;


import com.example.convert.UserConvert;
import com.example.domain.User;
import com.example.exception.AuthenticationException;
import com.example.repository.UserRepository;
import com.example.security.JwtTokenProvider;
import com.example.service.LoginService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserAuditableService, LoginService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
                .orElseThrow(() -> new UsernameNotFoundException("auth.invalidusername"));
    }

    @Override
    public Optional<User> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        final User u = (User) authentication.getPrincipal();

        return Optional.of(u);
    }

    @Transactional
    @Override
    public UserDTO login(String userName, String password) {

    	final User userEntity = (User) loadUserByUsername(userName);

    	if (userEntity == null) {
    		throw new AuthenticationException("auth.invalidusername");
    	}

    	if (!passwordEncoder.matches(password, userEntity.getPassword())) {
    		throw new AuthenticationException("auth.invalidpassword");
    	}

    	final UserDTO dto = userConvert.toDTO(userEntity);
    	dto.setToken(jwtTokenProvider.getToken(userEntity));

    	return dto;
    }

    @Override
    public Boolean logout(String token) {
        return false;
    }

    @Override
    public Boolean isValidToken(String token) {
        return false;
    }

    @Override
    public String createNewToken(String token) {
        return null;
    }

    @Override
    public Optional<UserDTO> save(UserDTO dto) {
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        final User user = this.userRepository.save(this.userConvert.fromDTO(dto));
        return Optional.ofNullable(user)
                .map(entity -> this.userConvert.toDTO(entity));
    }

    @Override
    public Optional<UserDTO> update(UserDTO dto) {
        final Optional<User> user = this.userRepository.findById(dto.getUserId());
        return user
                .map(entity -> this.userRepository.save(this.userConvert.fromDTO(entity, dto)))
                .map(entity -> this.userConvert.toDTO(entity));
    }

    @Override
    public Optional<UserDTO> delete(Long id) {
        final Optional<User> optional = this.userRepository.findById(id);

        final User audit = this.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
            entity.setIsActive(!entity.getIsActive());
            entity.setUpdated(LocalDateTime.now());
            entity.setUpdatedBy(audit);
            return entity;
        })
        .map(entity -> this.userRepository.save(entity))
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
