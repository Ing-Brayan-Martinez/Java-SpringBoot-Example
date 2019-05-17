package com.example.infraestructure.database.person;

import com.example.domain.person.Person;
import com.example.domain.person.PersonReposytoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonRepository implements PersonReposytoryInterface {

    //Atributos constantes.
    private final static Logger log = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    private DataSource conn;

    //Atributos.
    private volatile PreparedStatement ps;
    private volatile ResultSet res;
    private volatile Person dto;

    /**
     * Insertar una person.
     * @param data PersonCommandHandle a insert.
     */
    @Override
    public void insert(Person data) {

        final String SQL_INSERT = "INSERT INTO person (nombre, apellido, fechaNacimiento, cedula, altura, telefono, " +
                "correo, fechaRegistro, fechaModificacion) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_INSERT);
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

    }


    /**
     * Actualizar una person.
     * @param data PersonCommandHandle a update.
     */
    @Override
    public void update(Person data) {

        final String SQL_UPDATE = "UPDATE person SET nombre = ?, apellido = ?, fechaNacimiento = ?, cedula = ?, " +
        "altura = ?, telefono = ?, correo = ?, fechaModificacion = ? WHERE person.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_UPDATE);
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

    }


    /**
     * Eliminar una person.
     * @param key Clave primaria de la person a delete.
     */
    @Override
    public void delete(long key) {

        final String SQL_DELETE = "DELETE FROM person WHERE person.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_DELETE);
            this.ps.setLong(1, key);
            this.ps.executeUpdate() ;


        } catch (SQLException ex) {
            log.error(ex.getMessage());

        } finally {
            log.info("Se ha eliminado una persona");

        }

    }


    /**
     * Obtener una person.
     * @param key Clave primaria de la person a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Person select(long key) {

        final String SQL_SELECT = "SELECT * FROM person WHERE person.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_SELECT);
            this.ps.setLong(1, key);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                this.dto = new Person.Builder()
                    .withKey(res.getLong(1))
                    .withNombre(res.getString(2))
                    .withApellido(res.getString(3))
                    .withFechaNacimiento(res.getDate(4))
                    .withCedula(res.getString(5))
                    .withAltura(res.getFloat(6))
                    .withTelefono(res.getString(7))
                    .withCorreo(res.getString(8))
                    .withFechaRegistro(res.getDate(9))
                    .withFechaModificacion(res.getDate(10))
                    .build();
            }


        } catch (SQLException ex) {
            log.error(ex.getMessage());

        } finally {
            log.info("Se ha consultado una persona");

        }

        return this.dto;
    }

    /**
     * Obtener todas las person.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<Person> selectAll() {

        final String SQL_SELECT_ALL = "SELECT * FROM person;";
        final List<Person> list  = new ArrayList<>();

        try {
            this.ps  = this.conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {

                final Person person = new Person.Builder()
                    .withKey(res.getLong(1))
                    .withNombre(res.getString(2))
                    .withApellido(res.getString(3))
                    .withFechaNacimiento(res.getDate(4))
                    .withCedula(res.getString(5))
                    .withAltura(res.getFloat(6))
                    .withTelefono(res.getString(7))
                    .withCorreo(res.getString(8))
                    .withFechaRegistro(res.getDate(9))
                    .withFechaModificacion(res.getDate(10))
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
}
