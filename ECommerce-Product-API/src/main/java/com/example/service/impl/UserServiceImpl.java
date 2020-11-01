package com.example.service.impl;

import com.example.domain.UserMapper;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void edit(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(long id) {
        userMapper.deleteById(id);
    }
}


