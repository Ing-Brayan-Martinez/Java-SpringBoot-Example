package com.example.repository;

import com.example.domain.Role;

import java.util.List;
import java.util.Optional;


public interface RoleRepository {

    Role insert(Role role);

    Role update(Role role);

    Optional<Role> findById(Long roleId);

    List<Role> findAll();

    List<Role> findByUserId(Long userId);
}
