
package com.example.infraestructure.database.children;

import com.example.domain.children.Children;
import com.example.domain.children.ChildrenReposytoryInterface;
import com.example.infraestructure.database.Conexion;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChildrenRepository implements ChildrenReposytoryInterface {
    
    //Atributos constantes.
    private final Logger log = Logger.getLogger(ChildrenRepository.class);
    private final Conexion conn = Conexion.getInstance();

    //Atributos.
    private volatile PreparedStatement ps;
    private volatile ResultSet res; 
    private volatile Children dto;


    /**
     * Insertar un hijo.
     * @param data Hijo a insert.
     */
    @Override
    public void insertar(Children data) {
         
        final String SQL_INSERT = "INSERT INTO hijos (nombre, apellido, fechaNacimiento, tipoSangre, documentoIdentidad, KeyPersona, fechaRegistro, fechaModificacion) VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_INSERT);
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
            this.conn.close();
            this.log.info("Se ha insertado un hijo");

        }

    }
    
    
    /**
     * Actualizar un hijo.
     * @param data Hijo a update.
     */
    @Override
    public void actualizar(Children data) {
        
        final String SQL_UPDATE = "UPDATE hijos SET nombre = ?, apellido = ?, fechaNacimiento = ?, tipoSangre = ?, documentoIdentidad = ?, KeyPersona = ?, fechaModificacion = ? WHERE hijos.key = ?;";
       
        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_UPDATE);
            this.ps.setString (1,  data.getNombre());
            this.ps.setString (2,  data.getApellido());
            this.ps.setDate   (3,  data.getFechaNacimiento());
            this.ps.setString (4,  data.getTipoSangre());
            this.ps.setString (5,  data.getDocumentoIdentidad());
            this.ps.setInt    (6,  data.getKeyPersona());
            this.ps.setDate   (7,  data.getFechaModificacion());
            this.ps.setInt    (8,  data.getKey());


            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {            
            this.conn.close();
            this.log.info("Se ha actualisado un hijo");

        }
      
    }

    
    /**
     * Eliminar un hijo.
     * @param key Clave primaria del hijo a delete.
     */
    @Override
    public void eliminar(int key) {
        
        final String SQL_DELETE = "DELETE FROM hijos WHERE hijos.key = ?;";
        
        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_DELETE);
            this.ps.setInt(1, key);
            this.ps.executeUpdate();


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {            
            this.conn.close();
            this.log.info("Se ha eliminado un hijo");

        }

    }
    
    
    /**
     * Obtener un hijo.
     * @param key Clave primaria del hijo a select.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized Children consultar(int key) {
        
        final String SQL_SELECT = "SELECT * FROM hijos WHERE hijos.key = ?;";
       
        try {
            this.ps = this.conn.getConnection().prepareStatement(SQL_SELECT);
            this.ps.setInt(1, key);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
               this.dto = new Children(
                        this.res.getInt(1),
                        this.res.getString(2), 
                        this.res.getString(3), 
                        this.res.getDate(4),
                        this.res.getString(5),
                        this.res.getString(6),
                        this.res.getInt(7),
                        this.res.getDate(8),
                        this.res.getDate(9)
               );
            }


        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha consultado un hijo");

        }

        return this.dto;
    }
    
    /**
     * Obtener todos los children.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<Children> consultarTodoList() {
        
        final String SQL_SELECT_ALL = "SELECT * FROM hijos;";
        final List<Children> list = new ArrayList<>();

        try {
            this.ps  = this.conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            this.res = this.ps.executeQuery();


            while (this.res.next()) {
                list.add(new Children(
                        this.res.getInt(1),
                        this.res.getString(2), 
                        this.res.getString(3), 
                        this.res.getDate(4),
                        this.res.getString(5),
                        this.res.getString(6),
                        this.res.getInt(7),
                        this.res.getDate(8),
                        this.res.getDate(9)
                ));

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha consultado todos los hijos");

        }

        return list;
    }


}
