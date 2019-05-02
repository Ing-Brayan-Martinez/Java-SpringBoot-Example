package com.example.domain.childrenperson;



import java.sql.Date;

public class ChildrenPerson {


    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String tipoSangre;
    private String documentoIdentidad;
    private String representante;
    private int KeyPersona;
    private int key;

    public ChildrenPerson(String nombre, String apellido, Date fechaNacimiento, String tipoSangre, String documentoIdentidad, String representante, int keyPersona, int key) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.documentoIdentidad = documentoIdentidad;
        this.representante = representante;
        this.KeyPersona = keyPersona;
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public int getKeyPersona() {
        return KeyPersona;
    }

    public void setKeyPersona(int keyPersona) {
        KeyPersona = keyPersona;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ChildrenPersonCommandHandle{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", representante='" + representante + '\'' +
                ", KeyPersona=" + KeyPersona +
                ", key=" + key +
                '}';
    }
}
