package com.example.infraestructure.database.person;

import com.example.domain.person.Person;
import com.example.domain.person.PersonReposytoryInterface;
import com.example.infraestructure.database.Conexion;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;



@Repository
public class PersonRepository implements PersonReposytoryInterface {

    //Atributos constantes.
    private final Logger log = Logger.getLogger(PersonRepository.class);
    private final Conexion conn = Conexion.getInstance();


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

        final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, fechaNacimiento, cedula, altura, telefono, " +
                "correo, fechaRegistro, fechaModificacion) VALUES(?,?,?,?,?,?,?,?,?);";

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
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha insertado una persona");

        }

    }


    /**
     * Actualizar una person.
     * @param data PersonCommandHandle a update.
     */
    @Override
    public void update(Person data) {

        final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, fechaNacimiento = ?, cedula = ?, altura" +
                " = ?, telefono = ?, correo = ?, fechaModificacion = ? WHERE persona.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_UPDATE);
            this.ps.setString (1,  data.getNombre());
            this.ps.setString (2,  data.getApellido());
            this.ps.setObject (3,  data.getFechaNacimiento());
            this.ps.setString (4,  data.getCedula());
            this.ps.setFloat  (5,  data.getAltura());
            this.ps.setString (6,  data.getTelefono());
            this.ps.setString (7,  data.getCorreo());
            this.ps.setObject (8,  data.getFechaModificacion());
            this.ps.setInt    (9, data.getKey());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha actualisado una persona");

        }

    }


    /**
     * Eliminar una person.
     * @param key Clave primaria de la person a delete.
     */
    @Override
    public void delete(int key) {

        final String SQL_DELETE = "DELETE FROM persona WHERE persona.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_DELETE);
            this.ps.setInt(1, key);
            this.ps.executeUpdate() ;


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha eliminado una persona");

        }

    }


    /**
     * Obtener una person.
     * @param key Clave primaria de la person a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Person select(int key) {

        final String SQL_SELECT = "SELECT * FROM persona WHERE persona.key = ?;";

        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_SELECT);
            this.ps.setInt(1, key);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                this.dto = new Person(
                        this.res.getInt(1),
                        this.res.getString(2),
                        this.res.getString(3),
                        this.res.getDate(4),
                        this.res.getString(5),
                        this.res.getFloat(6),
                        this.res.getString(7),
                        this.res.getString(8),
                        this.res.getDate(9),
                        this.res.getDate(10)
                );
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha consultado una persona");

        }

        return this.dto;
    }

    /**
     * Obtener todas las person.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<Person> selectAll() {

        final String SQL_SELECT_ALL = "SELECT * FROM persona;";
        final List<Person> list  = new ArrayList<>();

        try {
            this.ps  = this.conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                list.add(new Person(
                        this.res.getInt(1),
                        this.res.getString(2),
                        this.res.getString(3),
                        this.res.getDate(4),
                        this.res.getString(5),
                        this.res.getFloat(6),
                        this.res.getString(7),
                        this.res.getString(8),
                        this.res.getDate(9),
                        this.res.getDate(10)
                ));

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha consultado todas las personas");

        }

        return list;
    }
}
