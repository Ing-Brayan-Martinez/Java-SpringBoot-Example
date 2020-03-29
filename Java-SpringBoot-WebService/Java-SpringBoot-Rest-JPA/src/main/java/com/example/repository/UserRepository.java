package com.example.repository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true AND u.userName = ?1 ")
    Optional<User> findByUserName(String userName);

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true AND u.userId = ?1")
    Optional<User> findById(Long userId);

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true ")
    List<User> findAll();

}
