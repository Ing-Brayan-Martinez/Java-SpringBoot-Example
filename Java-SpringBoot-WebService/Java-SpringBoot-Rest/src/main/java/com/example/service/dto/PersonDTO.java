package com.example.service.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PersonDTO {

    private Long key;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String cedula;
    private Float altura;
    private String telefono;
    private String correo;
    private Date fechaRegistro;
    private Date fechaModificacion;

}
