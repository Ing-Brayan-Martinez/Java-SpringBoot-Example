package com.example.service;


import com.example.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserAuditableService extends UserDetailsService {

    Optional<User> getCurrentUser();

}
