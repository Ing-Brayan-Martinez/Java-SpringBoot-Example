package com.example.service;


import com.example.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserAuditableService extends UserDetailsService {

    Optional<UserDTO> getCurrentUser();

}
