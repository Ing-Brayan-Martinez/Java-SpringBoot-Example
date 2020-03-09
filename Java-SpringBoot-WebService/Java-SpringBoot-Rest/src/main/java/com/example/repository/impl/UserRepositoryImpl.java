package com.example.repository.impl;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.repository.mapper.UserMapper;
import com.example.util.BooleanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public User create(User user) {
        final StringBuilder sql = new StringBuilder("INSERT INTO users(")
            .append(" created, is_active, updated, is_account_non_expired, ")
            .append(" is_account_non_locked, is_credentials_non_expired, name, password, ")
            .append(" username, created_by, updated_by, email, commission) ")
            .append(" VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?);");

        this.jdbcTemplateObject.update(sql.toString(), user.getName(), user.getPassword(), user.getUsername(),
        user.getCreatedBy(), user.getUpdatedBy(), user.getEmail(), user.getCommission());

        return findLastModified();
    }

    @Override
    public User update(User user) {
        final StringBuilder sql = new StringBuilder("UPDATE users ")
            .append(" SET is_active = ?, updated=?, is_account_non_expired=?,")
            .append(" is_account_non_locked=?, is_credentials_non_expired=?, ")
            .append(" name=?, password=?, username=?, updated_by=?, email=?, commission=? ")
            .append(" WHERE is_active = 'Y' AND user_id = ? ");

        this.jdbcTemplateObject.update(sql.toString(),BooleanConverter.toDatabaseColumn(user.getIsActive()), user.getUpdated(),
        BooleanConverter.toDatabaseColumn(user.getIsAccountNonExpired()), BooleanConverter.toDatabaseColumn(user.getIsAccountNonLocked()),
        BooleanConverter.toDatabaseColumn(user.getIsCredentialsNonExpired()), user.getName(),
        user.getPassword(), user.getUsername(), user.getUpdatedBy(), user.getEmail(),
        user.getCommission(), user.getUserId());

        return findLastModifiedById(user.getUserId());
    }

    @Override
    public Optional<User> findById(Long userId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y' AND u.user_id = ? ");

        final User role = this.jdbcTemplateObject.queryForObject(sql.toString(), new Object[]{userId}, new UserMapper());
        return Optional.ofNullable(role);
    }

    @Override
    public List<User> findAll() {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y'");

        return this.jdbcTemplateObject.query(sql.toString(), new UserMapper());
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y' AND u.username = ? ");

        final User user = this.jdbcTemplateObject.queryForObject(sql.toString(), new Object[]{userName}, new UserMapper());
        return Optional.ofNullable(user);
    }

    private User findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.user_id = (SELECT MAX(user_id) FROM users); ");

        return this.jdbcTemplateObject.queryForObject(sql.toString(), new UserMapper());
    }

    private User findLastModifiedById(Long userId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.user_id = ? ");

        return this.jdbcTemplateObject.queryForObject(sql.toString(), new Object[]{userId}, new UserMapper());
    }

    private StringBuilder getSelect() {
        return new StringBuilder("SELECT ")
            .append(" user_id, created, is_active, updated, is_account_non_expired, ")
            .append(" is_account_non_locked, is_credentials_non_expired, name, password, ")
            .append(" username, created_by, updated_by, email, commission  ")
            .append(" FROM users u ");
    }
}
