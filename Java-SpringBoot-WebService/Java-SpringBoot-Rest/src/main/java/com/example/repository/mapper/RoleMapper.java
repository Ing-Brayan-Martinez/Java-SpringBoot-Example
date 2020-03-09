package com.example.repository.mapper;


import com.example.domain.Role;
import com.example.util.BooleanConverter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Role role = new Role();
        role.setCreatedBy(rs.getLong("created_by"));
        role.setCreated(rs.getTimestamp("created"));
        role.setUpdatedBy(rs.getLong("updated_by"));
        role.setUpdated(rs.getTimestamp("updated"));
        role.setIsActive(BooleanConverter.toEntityAttribute(rs.getString("is_active")));

        role.setRoleId(rs.getLong("role_id"));
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));

        return role;
    }
}
