
package com.example.repository.impl;

import com.example.domain.Children;
import com.example.repository.ChildrenRepository;
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
public class ChildrenRepositoryImpl implements ChildrenRepository {

    //Atributos constantes.
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource conn;

    //Atributos.
    private PreparedStatement ps;
    private ResultSet res;
    private Children dto;


    /**
     * Insertar un hijo.
     * @param data Hijo a insert.
     */
    @Override
    public Children insert(Children data) {
        final StringBuilder sql = new StringBuilder("INSERT INTO children (")
            .append("created, is_active, updated, created_by, updated_by, ")
            .append("nombre, apellido, fecha_nacimiento, tipo_sangre, person_id)")
            .append(" VALUES (DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?) ");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong (1,  data.getCreatedBy());
            this.ps.setLong (2,  data.getUpdatedBy());
            this.ps.setString (3,  data.getNombre());
            this.ps.setString (4,  data.getApellido());
            this.ps.setDate   (5,  data.getFechaNacimiento());
            this.ps.setString (6,  data.getTipoSangre());
            this.ps.setLong (7,  data.getPersonId());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha insertado un hijo");

        }

        return findLastModified();
    }


    /**
     * Actualizar un hijo.
     * @param data Hijo a update.
     */
    @Override
    public Children update(Children data) {
        final StringBuilder sql = new StringBuilder(" UPDATE children SET ")
            .append("is_active = ?, updated = ?, updated_by = ?, ")
            .append("nombre = ?, apellido = ?, fecha_nacimiento = ?, tipo_sangre = ?, person_id = ? ")
            .append("WHERE children_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  BooleanConverter.toDatabaseColumn(data.getIsActive()));
            this.ps.setTimestamp(2,  data.getUpdated());
            this.ps.setLong(3,  data.getUpdatedBy());
            this.ps.setString(4,  data.getNombre());
            this.ps.setString(5,  data.getApellido());
            this.ps.setDate(6,  data.getFechaNacimiento());
            this.ps.setString(7,  data.getTipoSangre());
            this.ps.setLong(8,  data.getPersonId());
            this.ps.setLong(9,  data.getChildrenId());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha actualisado un hijo");

        }

        return findLastModifiedById(data.getChildrenId());
    }

    /**
     * Obtener un hijo.
     * @param key Clave primaria del hijo a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Optional<Children> findById(long key) {
        final StringBuilder sql = getSelect()
            .append(" WHERE h.children_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, key);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final Children children = new Children();
                children.setCreatedBy(res.getLong("created_by"));
                children.setCreated(res.getTimestamp("created"));
                children.setUpdatedBy(res.getLong("updated_by"));
                children.setUpdated(res.getTimestamp("updated"));
                children.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                children.setChildrenId(res.getLong("children_id"));
                children.setNombre(res.getString("nombre"));
                children.setApellido(res.getString("apellido"));
                children.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                children.setTipoSangre(res.getString("tipo_sangre"));
                children.setPersonId(res.getLong("person_id"));

                this.dto = children;

            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return Optional.empty();

        } finally {
            this.log.info("Se ha consultado un hijo");

        }

        return Optional.ofNullable(this.dto);
    }

    /**
     * Obtener todos los children.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<Children> findAll() {
        final StringBuilder sql = getSelect();
        final List<Children> list = new ArrayList<>();

        try (Connection con = this.conn.getConnection()) {
            this.ps  = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Children children = new Children();
                children.setCreatedBy(res.getLong("created_by"));
                children.setCreated(res.getTimestamp("created"));
                children.setUpdatedBy(res.getLong("updated_by"));
                children.setUpdated(res.getTimestamp("updated"));
                children.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                children.setChildrenId(res.getLong("children_id"));
                children.setNombre(res.getString("nombre"));
                children.setApellido(res.getString("apellido"));
                children.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                children.setTipoSangre(res.getString("tipo_sangre"));
                children.setPersonId(res.getLong("person_id"));

                list.add(children);

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return list;

        } finally {
            this.log.info("Se ha consultado todos los hijos");

        }

        return list;
    }

    private Children findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE h.children_id = (SELECT MAX(children_id) FROM children); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Children children = new Children();
                children.setCreatedBy(res.getLong("created_by"));
                children.setCreated(res.getTimestamp("created"));
                children.setUpdatedBy(res.getLong("updated_by"));
                children.setUpdated(res.getTimestamp("updated"));
                children.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                children.setChildrenId(res.getLong("children_id"));
                children.setNombre(res.getString("nombre"));
                children.setApellido(res.getString("apellido"));
                children.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                children.setTipoSangre(res.getString("tipo_sangre"));
                children.setPersonId(res.getLong("person_id"));

                this.dto = children;

            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha consultado el ultimo hijo agregado");

        }

        return this.dto;
    }

    private Children findLastModifiedById(Long childrenId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE h.children_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, childrenId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                final Children children = new Children();
                children.setCreatedBy(res.getLong("created_by"));
                children.setCreated(res.getTimestamp("created"));
                children.setUpdatedBy(res.getLong("updated_by"));
                children.setUpdated(res.getTimestamp("updated"));
                children.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                children.setChildrenId(res.getLong("children_id"));
                children.setNombre(res.getString("nombre"));
                children.setApellido(res.getString("apellido"));
                children.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                children.setTipoSangre(res.getString("tipo_sangre"));
                children.setPersonId(res.getLong("person_id"));

                this.dto = children;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha consultado el ultimo hijo modificado");

        }

        return this.dto;
    }

    private StringBuilder getSelect() {
        return new StringBuilder("SELECT h.* ")
            .append(" FROM children h ");
    }
}
