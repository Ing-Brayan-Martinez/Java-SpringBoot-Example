package com.example.repository.impl;

import com.example.domain.Person;
import com.example.repository.PersonRepository;
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
public class PersonRepositoryImpl implements PersonRepository {

    //Atributos constantes.
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource conn;

    //Atributos.
    private PreparedStatement ps;
    private ResultSet res;
    private Person dto;

    /**
     * Insertar una person.
     * @param data PersonCommandHandle a insert.
     */
    @Override
    public Person insert(Person data) {
        final StringBuilder sql = new StringBuilder("INSERT INTO person (")
            .append("created, is_active, updated, created_by, updated_by, ")
            .append("nombre, apellido, fecha_nacimiento, cedula, altura, telefono, correo)")
            .append(" VALUES (DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong (1,  data.getCreatedBy());
            this.ps.setLong (2,  data.getUpdatedBy());
            this.ps.setString (3,  data.getNombre());
            this.ps.setString (4,  data.getApellido());
            this.ps.setObject( 5,  data.getFechaNacimiento());
            this.ps.setString (6,  data.getCedula());
            this.ps.setFloat  (7,  data.getAltura());
            this.ps.setString (8,  data.getTelefono());
            this.ps.setString (9,  data.getCorreo());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            log.error(ex.getMessage());
            return null;

        } finally {
            log.info("Se ha insertado una persona");

        }

        return findLastModified();
    }


    /**
     * Actualizar una person.
     * @param data PersonCommandHandle a update.
     */
    @Override
    public Person update(Person data) {
        final StringBuilder sql = new StringBuilder("UPDATE person SET ")
            .append("is_active = ?, updated = ?, updated_by = ?, ")
            .append("nombre = ?, apellido = ?, fecha_nacimiento = ?, cedula = ?, ")
            .append("altura = ?, telefono = ?, correo = ? ")
            .append("WHERE person_id = ? ");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setString(1,  BooleanConverter.toDatabaseColumn(data.getIsActive()));
            this.ps.setTimestamp(2,  data.getUpdated());
            this.ps.setLong(3,  data.getUpdatedBy());
            this.ps.setString(4, data.getNombre());
            this.ps.setString(5, data.getApellido());
            this.ps.setDate(6, data.getFechaNacimiento());
            this.ps.setString(7, data.getCedula());
            this.ps.setFloat(8, data.getAltura());
            this.ps.setString(9, data.getTelefono());
            this.ps.setString(10, data.getCorreo());
            this.ps.setLong(11, data.getParsonId());

            this.ps.executeUpdate();


        } catch (SQLException ex) {
            log.error(ex.getMessage());
            return null;

        } finally {
            log.info("Se ha actualisado una persona");

        }

        return findLastModifiedById(data.getParsonId());
    }

    /**
     * Obtener una person.
     * @param key Clave primaria de la person a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Optional<Person> findById(long key) {
        final StringBuilder sql = getSelect()
            .append(" WHERE p.person_id = ? ");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, key);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Person person = new Person();
                person.setCreatedBy(res.getLong("created_by"));
                person.setCreated(res.getTimestamp("created"));
                person.setUpdatedBy(res.getLong("updated_by"));
                person.setUpdated(res.getTimestamp("updated"));
                person.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                person.setParsonId(res.getLong("person_id"));
                person.setNombre(res.getString("nombre"));
                person.setApellido(res.getString("apellido"));
                person.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                person.setCedula(res.getString("cedula"));
                person.setAltura(res.getFloat("altura"));
                person.setTelefono(res.getString("telefono"));
                person.setCorreo(res.getString("correo"));

                this.dto = person;

            }


        } catch (SQLException ex) {
            log.error(ex.getMessage());
            return Optional.empty();

        } finally {
            log.info("Se ha consultado una persona");

        }

        return Optional.ofNullable(this.dto);
    }

    /**
     * Obtener todas las person.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<Person> findAll() {
        final StringBuilder sql = getSelect();
        final List<Person> list  = new ArrayList<>();

        try (Connection con = this.conn.getConnection()) {

            this.ps  = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Person person = new Person();
                person.setCreatedBy(res.getLong("created_by"));
                person.setCreated(res.getTimestamp("created"));
                person.setUpdatedBy(res.getLong("updated_by"));
                person.setUpdated(res.getTimestamp("updated"));
                person.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                person.setParsonId(res.getLong("person_id"));
                person.setNombre(res.getString("nombre"));
                person.setApellido(res.getString("apellido"));
                person.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                person.setCedula(res.getString("cedula"));
                person.setAltura(res.getFloat("altura"));
                person.setTelefono(res.getString("telefono"));
                person.setCorreo(res.getString("correo"));

                list.add(person);

            }

        } catch (SQLException ex) {
            log.error(ex.getMessage());
            return list;

        } finally {
            log.info("Se ha consultado todas las personas");

        }

        return list;
    }

    private synchronized Person findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE p.person_id = (SELECT MAX(person_id) FROM person); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Person person = new Person();
                person.setCreatedBy(res.getLong("created_by"));
                person.setCreated(res.getTimestamp("created"));
                person.setUpdatedBy(res.getLong("updated_by"));
                person.setUpdated(res.getTimestamp("updated"));
                person.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                person.setParsonId(res.getLong("person_id"));
                person.setNombre(res.getString("nombre"));
                person.setApellido(res.getString("apellido"));
                person.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                person.setCedula(res.getString("cedula"));
                person.setAltura(res.getFloat("altura"));
                person.setTelefono(res.getString("telefono"));
                person.setCorreo(res.getString("correo"));

                this.dto = person;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha consultado la ultimo persona agregada");

        }

        return this.dto;
    }

    private synchronized Person findLastModifiedById(Long childrenId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE p.person_id = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, childrenId);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                final Person person = new Person();
                person.setCreatedBy(res.getLong("created_by"));
                person.setCreated(res.getTimestamp("created"));
                person.setUpdatedBy(res.getLong("updated_by"));
                person.setUpdated(res.getTimestamp("updated"));
                person.setIsActive(BooleanConverter.toEntityAttribute(res.getString("is_active")));

                person.setParsonId(res.getLong("person_id"));
                person.setNombre(res.getString("nombre"));
                person.setApellido(res.getString("apellido"));
                person.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                person.setCedula(res.getString("cedula"));
                person.setAltura(res.getFloat("altura"));
                person.setTelefono(res.getString("telefono"));
                person.setCorreo(res.getString("correo"));

                this.dto = person;
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());
            return null;

        } finally {
            this.log.info("Se ha consultado la ultimo persona modificada");

        }

        return this.dto;
    }

    private StringBuilder getSelect() {
        return new StringBuilder("SELECT p.* ")
            .append(" FROM person p ");
    }
}
