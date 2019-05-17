
package com.example.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


public class Children implements Serializable {

    public static final String KEY = "key";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String FECHA_NACIMIENTO = "fechaNacimiento";
    public static final String TIPO_SANGRE = "tipoSangre";
    public static final String DOCUMENTO_IDENTIDAD = "documentoIdentidad";
    public static final String PERSONA = "KeyPersona";
    public static final String FECHA_REGISTRO = "fechaRegistro";
    public static final String FECHA_MODIFICACION = "fechaModificacion";

    private long key;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String tipoSangre;
    private String documentoIdentidad;
    private int KeyPersona;
    private Date fechaRegistro;
    private Date fechaModificacion;

    public Children() {

    }

    public Children(long key, String nombre, String apellido, Date fechaNacimiento, String tipoSangre,
                    String documentoIdentidad, int KeyPersona, Date fechaRegistro, Date fechaModificacion) {
        this.key = key;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.documentoIdentidad = documentoIdentidad;
        this.KeyPersona = KeyPersona;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }

    public long getKey() {
        return key;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public int getKeyPersona() {
        return KeyPersona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Children children = (Children) o;
        return key == children.key &&
        KeyPersona == children.KeyPersona &&
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
        return Objects.hash(key, nombre, apellido, fechaNacimiento,
        tipoSangre, documentoIdentidad, KeyPersona, fechaRegistro,
        fechaModificacion);
    }

    @Override
    public String toString() {
        return "{" +
                "key=" + key +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", KeyPersona=" + KeyPersona +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    public static class Builder {

        private final Children object;

        public Builder() {
            object = new Children();
        }

        public Builder withKey(long value) {
            object.key = value;
            return this;
        }

        public Builder withNombre(String value) {
            object.nombre = value;
            return this;
        }

        public Builder withApellido(String value) {
            object.nombre = value;
            return this;
        }

        public Builder withFechaNacimiento(Date value) {
            object.fechaNacimiento = value;
            return this;
        }

        public Builder withTipoSangre(String value) {
            object.tipoSangre = value;
            return this;
        }

        public Builder withDocumentoIdentidad(String value) {
            object.documentoIdentidad = value;
            return this;
        }


        public Builder withKeyPersona(int value) {
            object.KeyPersona = value;
            return this;
        }

        public Builder withFechaRegistro(Date value) {
            object.fechaRegistro = value;
            return this;
        }

        public Builder withFechaModificacion(Date value) {
            object.fechaModificacion = value;
            return this;
        }

        public Children build() {
            return object;
        }
    }
}
