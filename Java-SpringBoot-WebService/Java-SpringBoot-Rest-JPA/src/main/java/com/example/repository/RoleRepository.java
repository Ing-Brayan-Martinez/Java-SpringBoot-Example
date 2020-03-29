package com.example.repository;

import com.example.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = " SELECT ro FROM Role ro WHERE ro.isActive = true AND ro.roleId = ?1")
    Optional<Role> findById(Long roleId);

    @Query(value = " SELECT ro FROM Role ro WHERE ro.isActive = true ")
    List<Role> findAll();

}
