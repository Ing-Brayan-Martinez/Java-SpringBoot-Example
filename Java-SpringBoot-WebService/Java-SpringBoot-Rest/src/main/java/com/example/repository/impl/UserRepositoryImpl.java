package com.example.repository.impl;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.util.BooleanConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    //Atributos constantes.
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource conn;

    //Atributos.
    private PreparedStatement ps;
    private ResultSet res;
    private User dto;

    @Override
    public User insert(User data) {
        final StringBuilder sql = new StringBuilder("INSERT INTO users (")
            .append("created, is_active, updated, is_account_non_expired, ")
            .append(" is_account_non_locked, is_credentials_non_expired, name, password, ")
            .append(" username, created_by, updated_by, email, commission) ")
            .append(" VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?);");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  data.getName());
            this.ps.setString(2,  data.getPassword());
            this.ps.setString(3,  data.getUsername());
            this.ps.setLong(4,  data.getCreatedBy());
            this.ps.setLong(5,  data.getUpdatedBy());
            this.ps.setString(6,  data.getEmail());
            this.ps.setFloat(7,  data.getCommission());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha insertado un hijo");

        }

        return findLastModified();
    }

    @Override
    public User update(User data) {
        final StringBuilder sql = new StringBuilder("UPDATE users ")
            .append(" SET is_active = ?, updated=?, is_account_non_expired=?,")
            .append(" is_account_non_locked=?, is_credentials_non_expired=?, ")
            .append(" name=?, password=?, username=?, updated_by=?, email=?, commission=? ")
            .append(" WHERE is_active = 'Y' AND user_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  BooleanConverter.toDatabaseColumn(data.getIsActive()));
            this.ps.setTimestamp(2,  data.getUpdated());
            this.ps.setString(3,  BooleanConverter.toDatabaseColumn(data.getIsAccountNonExpired()));
            this.ps.setString(4,  BooleanConverter.toDatabaseColumn(data.getIsAccountNonLocked()));
            this.ps.setString(5,  BooleanConverter.toDatabaseColumn(data.getIsCredentialsNonExpired()));
            this.ps.setString(6,  data.getName());
            this.ps.setString(7,  data.getPassword());
            this.ps.setString(8,  data.getUsername());
            this.ps.setLong(9,  data.getUpdatedBy());
            this.ps.setString(10,  data.getEmail());
            this.ps.setFloat(11,  data.getCommission());
            this.ps.setLong(12,  data.getUserId());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha actualisado un hijo");

        }

        return findLastModifiedById(data.getUserId());
    }

    @Override
    public synchronized Optional<User> findById(Long userId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y' AND u.user_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, userId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final User user = new User();
                user.setCreatedBy(res.getLong("created_by"));
                user.setCreated(res.getTimestamp("created"));
                user.setUpdatedBy(res.getLong("updated_by"));
                user.setUpdated(res.getTimestamp("updated"));
                user.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                user.setUserId(res.getLong("user_id"));
                user.setName(res.getString("name"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setCommission(res.getFloat("commission"));
                user.setPassword(res.getString("password"));
                user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_account_non_expired")));
                user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(res.getString("is_account_non_locked")));
                user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_credentials_non_expired")));

                this.dto = user;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return Optional.empty();

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return Optional.ofNullable(this.dto);
    }

    @Override
    public synchronized List<User> findAll() {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y'");

        final List<User> list = new ArrayList<>();

        try (Connection con = this.conn.getConnection()) {
            this.ps  = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final User user = new User();
                user.setCreatedBy(res.getLong("created_by"));
                user.setCreated(res.getTimestamp("created"));
                user.setUpdatedBy(res.getLong("updated_by"));
                user.setUpdated(res.getTimestamp("updated"));
                user.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                user.setUserId(res.getLong("user_id"));
                user.setName(res.getString("name"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setCommission(res.getFloat("commission"));
                user.setPassword(res.getString("password"));
                user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_account_non_expired")));
                user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(res.getString("is_account_non_locked")));
                user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_credentials_non_expired")));

                list.add(user);

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return list;

        } finally {
            this.log.info("Se ha consultado todos los hijos");

        }

        return list;
    }

    @Override
    public synchronized Optional<User> findByUserName(String userName) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.is_active = 'Y' AND u.username = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1, userName);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final User user = new User();
                user.setCreatedBy(res.getLong("created_by"));
                user.setCreated(res.getTimestamp("created"));
                user.setUpdatedBy(res.getLong("updated_by"));
                user.setUpdated(res.getTimestamp("updated"));
                user.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                user.setUserId(res.getLong("user_id"));
                user.setName(res.getString("name"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setCommission(res.getFloat("commission"));
                user.setPassword(res.getString("password"));
                user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_account_non_expired")));
                user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(res.getString("is_account_non_locked")));
                user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_credentials_non_expired")));

                this.dto = user;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return Optional.empty();

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return Optional.ofNullable(this.dto);
    }

    private synchronized User findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.user_id = (SELECT MAX(user_id) FROM users); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final User user = new User();
                user.setCreatedBy(res.getLong("created_by"));
                user.setCreated(res.getTimestamp("created"));
                user.setUpdatedBy(res.getLong("updated_by"));
                user.setUpdated(res.getTimestamp("updated"));
                user.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                user.setUserId(res.getLong("user_id"));
                user.setName(res.getString("name"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setCommission(res.getFloat("commission"));
                user.setPassword(res.getString("password"));
                user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_account_non_expired")));
                user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(res.getString("is_account_non_locked")));
                user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_credentials_non_expired")));

                this.dto = user;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return this.dto;
    }

    private synchronized User findLastModifiedById(Long userId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE u.user_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, userId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final User user = new User();
                user.setCreatedBy(res.getLong("created_by"));
                user.setCreated(res.getTimestamp("created"));
                user.setUpdatedBy(res.getLong("updated_by"));
                user.setUpdated(res.getTimestamp("updated"));
                user.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                user.setUserId(res.getLong("user_id"));
                user.setName(res.getString("name"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setCommission(res.getFloat("commission"));
                user.setPassword(res.getString("password"));
                user.setIsAccountNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_account_non_expired")));
                user.setIsAccountNonLocked(BooleanConverter.toEntityAttribute(res.getString("is_account_non_locked")));
                user.setIsCredentialsNonExpired(BooleanConverter.toEntityAttribute(res.getString("is_credentials_non_expired")));

                this.dto = user;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return this.dto;
    }

    private StringBuilder getSelect() {
        return new StringBuilder("SELECT ")
            .append("user_id, created, is_active, updated, is_account_non_expired,")
            .append(" is_account_non_locked, is_credentials_non_expired, name, password,")
            .append(" username, created_by, updated_by, email, commission")
            .append(" FROM users u ");
    }
}
