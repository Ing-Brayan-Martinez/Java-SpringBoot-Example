package com.example.infraestructure.database.childrenperson;

import com.example.domain.childrenperson.ChildrenPerson;
import com.example.domain.childrenperson.ChildrenPersonReposytoryInterface;
import com.example.infraestructure.database.Conexion;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChildrenPersonRepository implements ChildrenPersonReposytoryInterface {

    //Atributos constantes.
    private final Logger log = Logger.getLogger(ChildrenPersonRepository.class);
    private final Conexion conn = Conexion.getInstance();

    //Atributos.
    private volatile PreparedStatement ps;
    private volatile ResultSet res;


    /**
     * Obtener todos los children.
     * @return Resultado de la consulta.
     */
    @Override
    public synchronized List<ChildrenPerson> consultarHijosPersonas() {

        final String SQL_SELECT = "SELECT hijos.`nombre`, hijos.`apellido`, hijos.`fechaNacimiento`, hijos.`tiposangre`, hijos.`documentoIdentidad`, persona.`nombre`, persona.`apellido`, hijos.`KeyPersona`, persona.`key` FROM hijos JOIN persona ON hijos.`KeyPersona` = persona.`key`;";
        final List<ChildrenPerson> list = new ArrayList<>();

        try {
            this.ps  = this.conn.getConnection().prepareStatement(SQL_SELECT);
            this.res = this.ps.executeQuery();

            while (this.res.next()) {
                list.add(new ChildrenPerson(
                        this.res.getString(1),
                        this.res.getString(2),
                        this.res.getDate(3),
                        this.res.getString(4),
                        this.res.getString(5),
                        this.res.getString(6)+" "+this.res.getString(7),
                        this.res.getInt(8),
                        this.res.getInt(9)
                ));

            }

        } catch (SQLException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.conn.close();
            this.log.info("Se ha consultado todas las personas e hijos");

        }

        return list;
    }

}
