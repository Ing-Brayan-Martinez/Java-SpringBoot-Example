package com.example.infraestructure.convert.children;

import com.example.domain.children.Children;
import com.example.domain.ConvertedInterface;
import com.example.infraestructure.util.Intent;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ChildrenConverted implements ConvertedInterface<Children> {

    private final Logger log = Logger.getLogger(ChildrenConverted.class);

    @Override
    public Children onConvert(Intent command) {
        return new Children.Builder()
            .withKey((Long) command.getExtra(Children.KEY))
            .withNombre((String) command.getExtra(Children.NOMBRE))
            .withApellido((String) command.getExtra(Children.APELLIDO))
            .withFechaNacimiento((Date) command.getExtra(Children.FECHA_NACIMIENTO))
            .withTipoSangre((String) command.getExtra(Children.TIPO_SANGRE))
            .withDocumentoIdentidad((String) command.getExtra(Children.DOCUMENTO_IDENTIDAD))
            .withKeyPersona((Integer) command.getExtra(Children.PERSONA))
            .withFechaRegistro((Date) command.getExtra(Children.FECHA_REGISTRO))
            .withFechaModificacion((Date) command.getExtra(Children.FECHA_MODIFICACION))
            .build();
    }
}
