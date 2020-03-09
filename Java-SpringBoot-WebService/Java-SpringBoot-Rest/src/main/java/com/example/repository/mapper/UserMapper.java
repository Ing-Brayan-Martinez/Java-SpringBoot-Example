package com.example.repository.mapper;


import com.example.domain.User;
import com.example.util.BooleanConverter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        final User user = new User();
        user.setCreatedBy(rs.getLong("created_by"));
        user.setCreated(rs.getTimestamp("created"));
        user.setUpdatedBy(rs.getLong("updated_by"));
        user.setUpdated(rs.getTimestamp("updated"));
        user.setIsActive(BooleanConverter.toEntityAttribute(rs.getString("is_active")));

        user.setUserId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setCommission(rs.getFloat("commission"));
        user.setPassword(rs.getString("password"));
        user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(rs.getString("is_account_non_expired")));
        user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(rs.getString("is_account_non_locked")));
        user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(rs.getString("is_credentials_non_expired")));

        return user;
    }

}
