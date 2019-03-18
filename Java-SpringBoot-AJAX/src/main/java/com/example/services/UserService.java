package com.example.services;

import com.example.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;

    // Love Java 8
    public List<User> findByUserNameOrEmail(String username) {

        final List<User> result = users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return result;

    }

    // Init some users for testing
    @PostConstruct
    private void iniDataForTesting() {

        users = new ArrayList<>();

        users.add(new User("brayan", "12345678", "brayanmartinez827@gmail.com"));
        users.add(new User("example", "password111", "example@yahoo.com"));
        users.add(new User("yflow", "password222", "yflow@yahoo.com"));
        users.add(new User("laplap", "password333", "example@yahoo.com"));

    }

}
