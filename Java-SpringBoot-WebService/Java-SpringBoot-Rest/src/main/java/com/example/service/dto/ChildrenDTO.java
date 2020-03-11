package com.example.service.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ChildrenDTO {

    private long childrenId;

    private String nombre;

    private String apellido;

    private Date fechaNacimiento;

    private String tipoSangre;

    private Long personId;

}
