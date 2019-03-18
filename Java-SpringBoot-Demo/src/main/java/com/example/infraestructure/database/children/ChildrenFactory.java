package com.example.infraestructure.database.children;

import com.example.aplication.children.ChildrenCommand;
import com.example.domain.children.Children;
import com.example.domain.children.ChildrenFactoryInterface;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;




@Component
public class ChildrenFactory implements ChildrenFactoryInterface {

    private final Logger log = Logger.getLogger(ChildrenFactory.class);

    @Override
    public Children toChildren(ChildrenCommand command) {
        Children children = null;

        try {

            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            final Date fechaNacimiento2   = new Date(format.parse(command.getFechaNacimiento()).getTime());
            children = new Children(
                Integer.valueOf(command.getKey()),
                command.getNombre(),
                command.getApellido(),
                fechaNacimiento2,
                command.getTipoSangre(),
                command.getDocumentoIdentidad(),
                Integer.valueOf(command.getKeyPersona()),
                command.getFechaRegistro(),
                command.getFechaModificacion()
            );



        } catch (ParseException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha construido un hijo.");

        }

        return children;
    }
}
