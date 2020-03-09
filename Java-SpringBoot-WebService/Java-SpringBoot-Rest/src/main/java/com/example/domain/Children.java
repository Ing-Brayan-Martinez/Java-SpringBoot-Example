
package com.example.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;


@Data
@Builder
public class Children implements Serializable {

    private long key;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String tipoSangre;
    private String documentoIdentidad;
    private int keyPersona;
    private Date fechaRegistro;
    private Date fechaModificacion;


}
