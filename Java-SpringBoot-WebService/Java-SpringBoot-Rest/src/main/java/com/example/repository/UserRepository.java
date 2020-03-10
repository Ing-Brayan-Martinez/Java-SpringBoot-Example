package com.example.repository;


import com.example.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    User update(User user);

    Optional<User> findById(Long userId);

    List<User> findAll();

    Optional<User> findByUserName(String userName);

}
