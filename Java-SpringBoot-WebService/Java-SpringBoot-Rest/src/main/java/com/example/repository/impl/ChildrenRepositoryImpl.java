
package com.example.repository.impl;

import com.example.domain.Children;
import com.example.repository.ChildrenReposytory;
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
public class ChildrenRepositoryImpl implements ChildrenReposytory {

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

        final String sql = "INSERT INTO children (" +
            "nombre, apellido, fechaNacimiento, tipoSangre, documentoIdentidad, " +
            "keyPersona, fechaRegistro, fechaModificacion)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = this.conn.getConnection()) {

            this.ps = con.prepareStatement(sql);
            this.ps.setString (1,  data.getNombre());
            this.ps.setString (2,  data.getApellido());
            this.ps.setDate   (3,  data.getFechaNacimiento());
            this.ps.setString (4,  data.getTipoSangre());
            this.ps.setString (5,  data.getDocumentoIdentidad());
            this.ps.setInt    (6,  data.getKeyPersona());
            this.ps.setDate   (7,  data.getFechaRegistro());
            this.ps.setDate   (8,  data.getFechaModificacion());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

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

        final String sql = " UPDATE children " +
            " SET nombre = ?, apellido = ?, fechaNacimiento = ?, tipoSangre = ?," +
            " documentoIdentidad = ?, KeyPersona = ?, fechaModificacion = ? " +
            " WHERE children.key = ? ";

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql);
            this.ps.setString (1,  data.getNombre());
            this.ps.setString (2,  data.getApellido());
            this.ps.setDate   (3,  data.getFechaNacimiento());
            this.ps.setString (4,  data.getTipoSangre());
            this.ps.setString (5,  data.getDocumentoIdentidad());
            this.ps.setInt    (6,  data.getKeyPersona());
            this.ps.setDate   (7,  data.getFechaModificacion());
            this.ps.setLong   (8,  data.getKey());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha actualisado un hijo");

        }

        return findLastModifiedById(data.getKey());
    }

    /**
     * Obtener un hijo.
     * @param key Clave primaria del hijo a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Optional<Children> findById(long key) {

        final StringBuilder sql = getSelect()
            .append(" WHERE h.key = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, key);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
              this.dto = Children.builder()
                .key(this.res.getInt(1))
                .nombre(this.res.getString(2))
                .apellido(this.res.getString(3))
                .fechaNacimiento(this.res.getDate(4))
                .tipoSangre(this.res.getString(5))
                .documentoIdentidad(this.res.getString(6))
                .keyPersona(this.res.getInt(7))
                .fechaRegistro(this.res.getDate(8))
                .fechaModificacion(this.res.getDate(9))
                .build();

            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

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
                final Children children = Children.builder()
                    .key(this.res.getInt(1))
                    .nombre(this.res.getString(2))
                    .apellido(this.res.getString(3))
                    .fechaNacimiento(this.res.getDate(4))
                    .tipoSangre(this.res.getString(5))
                    .documentoIdentidad(this.res.getString(6))
                    .keyPersona(this.res.getInt(7))
                    .fechaRegistro(this.res.getDate(8))
                    .fechaModificacion(this.res.getDate(9))
                    .build();

                list.add(children);

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha consultado todos los hijos");

        }

        return list;
    }

    private Children findLastModified() {
        final StringBuilder sql = getSelect()
            .append(" WHERE h.key = (SELECT MAX(key) FROM children); ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                this.dto = Children.builder()
                    .key(this.res.getInt(1))
                    .nombre(this.res.getString(2))
                    .apellido(this.res.getString(3))
                    .fechaNacimiento(this.res.getDate(4))
                    .tipoSangre(this.res.getString(5))
                    .documentoIdentidad(this.res.getString(6))
                    .keyPersona(this.res.getInt(7))
                    .fechaRegistro(this.res.getDate(8))
                    .fechaModificacion(this.res.getDate(9))
                    .build();

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
            .append(" WHERE h.key = ? ");

        try (Connection con = this.conn.getConnection()) {
            this.ps = con.prepareStatement(sql.toString());
            this.ps.setLong(1, childrenId);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                this.dto = Children.builder()
                    .key(this.res.getInt(1))
                    .nombre(this.res.getString(2))
                    .apellido(this.res.getString(3))
                    .fechaNacimiento(this.res.getDate(4))
                    .tipoSangre(this.res.getString(5))
                    .documentoIdentidad(this.res.getString(6))
                    .keyPersona(this.res.getInt(7))
                    .fechaRegistro(this.res.getDate(8))
                    .fechaModificacion(this.res.getDate(9))
                    .build();

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
