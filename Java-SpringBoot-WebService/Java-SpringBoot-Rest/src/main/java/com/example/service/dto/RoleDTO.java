package com.example.service.dto;

import java.util.Objects;

public class RoleDTO {

    private long roleId;

    private String name;

    private String description;

    public RoleDTO() {
    }

    public RoleDTO(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return roleId == roleDTO.roleId &&
        Objects.equals(name, roleDTO.name) &&
        Objects.equals(description, roleDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name, description);
    }


}
