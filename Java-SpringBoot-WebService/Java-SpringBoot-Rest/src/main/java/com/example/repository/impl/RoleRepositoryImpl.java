package com.example.repository.impl;

import com.example.domain.Role;
import com.example.repository.RoleRepository;
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
public class RoleRepositoryImpl implements RoleRepository {

    //Atributos constantes.
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource conn;

    //Atributos.
    private PreparedStatement ps;
    private ResultSet res;
    private Role dto;

    @Override
    public Role insert(Role data) {
        final StringBuilder sql = new StringBuilder("INSERT INTO role (")
            .append("created, is_active, updated, name, description, created_by, updated_by)")
            .append(" VALUES (DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?);");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  data.getName());
            this.ps.setString(2,  data.getDescription());
            this.ps.setLong(3,  data.getCreatedBy());
            this.ps.setLong(4,  data.getUpdatedBy());


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
    public Role update(Role data) {
        final StringBuilder sql = new StringBuilder("UPDATE role ")
            .append(" SET  is_active = ?, updated = ?, updated_by = ?, name = ?, description = ? ")
            .append(" WHERE is_active = 'Y' AND role_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  BooleanConverter.toDatabaseColumn(data.getIsActive()));
            this.ps.setTimestamp(2,  data.getUpdated());
            this.ps.setLong(3,  data.getUpdatedBy());
            this.ps.setString(4,  data.getName());
            this.ps.setString(5,  data.getDescription());
            this.ps.setLong(6,  data.getRoleId());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha actualisado un hijo");

        }

        return findLastModifiedById(data.getRoleId());
    }

    @Override
    public synchronized Optional<Role> findById(Long roleId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE ro.is_active = 'Y' AND ro.role_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, roleId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final Role role = new Role();
                role.setCreatedBy(res.getLong("created_by"));
                role.setCreated(res.getTimestamp("created"));
                role.setUpdatedBy(res.getLong("updated_by"));
                role.setUpdated(res.getTimestamp("updated"));
                role.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                role.setRoleId(res.getLong("role_id"));
                role.setName(res.getString("name"));
                role.setDescription(res.getString("description"));

                this.dto = role;
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
    public synchronized List<Role> findAll() {
        final StringBuilder sql = getSelect()
            .append(" WHERE ro.is_active = 'Y' ");

        final List<Role> list = new ArrayList<>();

        try (Connection con = this.conn.getConnection()) {
            this.ps  = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Role role = new Role();
                role.setCreatedBy(res.getLong("created_by"));
                role.setCreated(res.getTimestamp("created"));
                role.setUpdatedBy(res.getLong("updated_by"));
                role.setUpdated(res.getTimestamp("updated"));
                role.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                role.setRoleId(res.getLong("role_id"));
                role.setName(res.getString("name"));
                role.setDescription(res.getString("description"));

                list.add(role);

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
    public synchronized List<Role> findByUserId(Long userId) {
        final StringBuilder sql = getSelect()
            .append(" INNER JOIN user_role ur ON ro.role_id = ur.role_id")
            .append(" WHERE ro.is_active = 'Y' AND ur.user_id = ? ");

        final List<Role> list = new ArrayList<>();

        try (Connection con = this.conn.getConnection()) {
            this.ps  = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Role role = new Role();
                role.setCreatedBy(res.getLong("created_by"));
                role.setCreated(res.getTimestamp("created"));
                role.setUpdatedBy(res.getLong("updated_by"));
                role.setUpdated(res.getTimestamp("updated"));
                role.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                role.setRoleId(res.getLong("role_id"));
                role.setName(res.getString("name"));
                role.setDescription(res.getString("description"));

                list.add(role);

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return list;

        } finally {
            this.log.info("Se ha consultado todos los hijos");

        }

        return list;
    }

    private synchronized Role findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE ro.role_id = (SELECT MAX(role_id) FROM role); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final Role role = new Role();
                role.setCreatedBy(res.getLong("created_by"));
                role.setCreated(res.getTimestamp("created"));
                role.setUpdatedBy(res.getLong("updated_by"));
                role.setUpdated(res.getTimestamp("updated"));
                role.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                role.setRoleId(res.getLong("role_id"));
                role.setName(res.getString("name"));
                role.setDescription(res.getString("description"));

                this.dto = role;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return this.dto;
    }

    private synchronized Role findLastModifiedById(Long roleId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE ro.role_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, roleId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final Role role = new Role();
                role.setCreatedBy(res.getLong("created_by"));
                role.setCreated(res.getTimestamp("created"));
                role.setUpdatedBy(res.getLong("updated_by"));
                role.setUpdated(res.getTimestamp("updated"));
                role.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                role.setRoleId(res.getLong("role_id"));
                role.setName(res.getString("name"));
                role.setDescription(res.getString("description"));

                this.dto = role;
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
            .append(" ro.role_id, ro.created, ro.is_active, ro.updated, ro.description, ro.name, ro.created_by, ro.updated_by ")
            .append(" FROM role ro ");
    }
}
