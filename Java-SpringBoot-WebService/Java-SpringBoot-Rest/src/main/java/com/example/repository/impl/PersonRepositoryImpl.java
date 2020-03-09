package com.example.repository.impl;

import com.example.domain.Person;
import com.example.repository.PersonReposytory;
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
public class PersonRepositoryImpl implements PersonReposytory {

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

        final String SQL_INSERT = "INSERT INTO person (nombre, apellido, fechaNacimiento, cedula, altura, telefono, " +
                "fechaRegistro, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(SQL_INSERT);
            this.ps.setString (1,  data.getNombre());
            this.ps.setString (2,  data.getApellido());
            this.ps.setObject( 3,  data.getFechaNacimiento());
            this.ps.setString (4,  data.getCedula());
            this.ps.setFloat  (5,  data.getAltura());
            this.ps.setString (6,  data.getTelefono());
            this.ps.setString (7,  data.getCorreo());
            this.ps.setObject (8,  data.getFechaRegistro());
            this.ps.setObject (9,  data.getFechaModificacion());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            log.error(ex.getMessage());

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

        final String SQL_UPDATE = "UPDATE person SET nombre = ?, apellido = ?, fechaNacimiento = ?, cedula = ?, " +
        "altura = ?, telefono = ?, fechaModificacion = ? WHERE person.key = ?;";

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(SQL_UPDATE);
            this.ps.setString(1, data.getNombre());
            this.ps.setString(2, data.getApellido());
            this.ps.setDate(3, data.getFechaNacimiento());
            this.ps.setString(4, data.getCedula());
            this.ps.setFloat(5, data.getAltura());
            this.ps.setString(6, data.getTelefono());
            this.ps.setString(7, data.getCorreo());
            this.ps.setDate(8, data.getFechaModificacion());
            this.ps.setLong(9, data.getKey());

            this.ps.executeUpdate();


        } catch (SQLException ex) {
            log.error(ex.getMessage());

        } finally {
            log.info("Se ha actualisado una persona");

        }

        return findLastModifiedById(data.getKey());
    }

    /**
     * Obtener una person.
     * @param key Clave primaria de la person a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Optional<Person> findById(long key) {

        final StringBuilder sql = getSelect()
            .append(" WHERE p.key = ? ");

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, key);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                this.dto = Person.builder()
                    .key(res.getLong(1))
                    .nombre(res.getString(2))
                    .apellido(res.getString(3))
                    .fechaNacimiento(res.getDate(4))
                    .cedula(res.getString(5))
                    .altura(res.getFloat(6))
                    .telefono(res.getString(7))
                    .correo(res.getString(8))
                    .fechaRegistro(res.getDate(9))
                    .fechaModificacion(res.getDate(10))
                    .build();

            }


        } catch (SQLException ex) {
            log.error(ex.getMessage());

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

                final Person person = Person.builder()
                    .key(res.getLong(1))
                    .nombre(res.getString(2))
                    .apellido(res.getString(3))
                    .fechaNacimiento(res.getDate(4))
                    .cedula(res.getString(5))
                    .altura(res.getFloat(6))
                    .telefono(res.getString(7))
                    .correo(res.getString(8))
                    .fechaRegistro(res.getDate(9))
                    .fechaModificacion(res.getDate(10))
                    .build();

                list.add(person);

            }

        } catch (SQLException ex) {
            log.error(ex.getMessage());

        } finally {
            log.info("Se ha consultado todas las personas");

        }

        return list;
    }

    private Person findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE p.key = (SELECT MAX(key) FROM person); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                this.dto = Person.builder()
                    .key(res.getLong(1))
                    .nombre(res.getString(2))
                    .apellido(res.getString(3))
                    .fechaNacimiento(res.getDate(4))
                    .cedula(res.getString(5))
                    .altura(res.getFloat(6))
                    .telefono(res.getString(7))
                    .correo(res.getString(8))
                    .fechaRegistro(res.getDate(9))
                    .fechaModificacion(res.getDate(10))
                    .build();

            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha consultado la ultimo persona agregada");

        }

        return this.dto;
    }

    private Person findLastModifiedById(Long childrenId) {
        final StringBuilder sql = getSelect()
            .append(" WHERE p.key = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, childrenId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                this.dto = Person.builder()
                    .key(res.getLong(1))
                    .nombre(res.getString(2))
                    .apellido(res.getString(3))
                    .fechaNacimiento(res.getDate(4))
                    .cedula(res.getString(5))
                    .altura(res.getFloat(6))
                    .telefono(res.getString(7))
                    .correo(res.getString(8))
                    .fechaRegistro(res.getDate(9))
                    .fechaModificacion(res.getDate(10))
                    .build();

            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

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
