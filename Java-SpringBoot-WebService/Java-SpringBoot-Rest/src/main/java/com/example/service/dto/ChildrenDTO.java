package com.example.service.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ChildrenDTO {

    private long key;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String tipoSangre;
    private String documentoIdentidad;
    private int KeyPersona;
    private Date fechaRegistro;
    private Date fechaModificacion;

}
