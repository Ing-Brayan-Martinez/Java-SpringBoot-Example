package com.example.domain;

import com.example.entity.User;

import java.util.List;

public interface UserMapper {


    void insert(User entity);

    void update(User entity);

    void deleteById(long id);


    User findById(long id);


    List<User> findAll();
}
