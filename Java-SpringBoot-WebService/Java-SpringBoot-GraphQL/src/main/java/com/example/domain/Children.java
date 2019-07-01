
package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "Children", catalog = "adempiere")
public class Children implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "KeyChildren")
    private Long keyChildren;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "TipoSangre")
    private String tipoSangre;

    @Column(name = "DocumentoIdentidad")
    private String documentoIdentidad;

    @ManyToOne
    @JoinColumn(name = "KeyPerson", referencedColumnName = "KeyPerson")
    private Person keyPersona;

    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @Column(name = "FechaModificacion")
    private Date fechaModificacion;

    public Children() {

    }

    public long getKeyChildren() {
        return keyChildren;
    }

    public void setKeyChildren(long keyChildren) {
        this.keyChildren = keyChildren;
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

    public Person getKeyPersona() {
        return keyPersona;
    }

    public void setKeyPersona(Person keyPersona) {
        this.keyPersona = keyPersona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put("keyChildren", keyChildren);
        map.put("nombre", nombre);
        map.put("apellido", apellido);
        map.put("fechaNacimiento", fechaNacimiento);
        map.put("tipoSangre", tipoSangre);
        map.put("documentoIdentidad", documentoIdentidad);
        map.put("fechaRegistro", fechaRegistro);
        map.put("fechaModificacion", fechaModificacion);

        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Children children = (Children) o;
        return keyChildren == children.keyChildren &&
        keyPersona == children.keyPersona &&
        Objects.equals(nombre, children.nombre) &&
        Objects.equals(apellido, children.apellido) &&
        Objects.equals(fechaNacimiento, children.fechaNacimiento) &&
        Objects.equals(tipoSangre, children.tipoSangre) &&
        Objects.equals(documentoIdentidad, children.documentoIdentidad) &&
        Objects.equals(fechaRegistro, children.fechaRegistro) &&
        Objects.equals(fechaModificacion, children.fechaModificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyChildren, nombre, apellido, fechaNacimiento,
        tipoSangre, documentoIdentidad, keyPersona, fechaRegistro,
        fechaModificacion);
    }

    @Override
    public String toString() {
        return "{" +
                "keyChildren=" + keyChildren +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", keyPersona=" + keyPersona +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

}
