
package com.ingbrayanmartinez.fabricabeans.curso;


import java.io.Serializable;

public class Curso implements Serializable {

    private int id;
    private String codigo;
    private String descripcion;
    private int cantidaAlumnos;

    public Curso() {
    }


    public Curso(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidaAlumnos() {
        return cantidaAlumnos;
    }

    public void setCantidaAlumnos(int cantidaAlumnos) {
        this.cantidaAlumnos = cantidaAlumnos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidaAlumnos=" + cantidaAlumnos +
                '}';
    }
}