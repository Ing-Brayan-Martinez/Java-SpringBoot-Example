package com.example.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDTO {

    private long userId;

    private String name;

    private String userName;

    @Getter(onMethod = @__( @JsonIgnore ))
    private String password;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;

    private List<RoleDTO> roles = new ArrayList<>();

    private String token;

}
