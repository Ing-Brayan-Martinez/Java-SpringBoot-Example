/*
 * Copyright 2017 brayan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "Person", catalog = "adempiere")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "KeyPerson")
    private Long keyPerson;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "Cedula")
    private String cedula;

    @Column(name = "Altura")
    private Float altura;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @Column(name = "FechaModificacion")
    private Date fechaModificacion;

    public Person() {
    }

    public Long getKeyPerson() {
        return keyPerson;
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

    public String getCedula() {
        return cedula;
    }

    public Float getAltura() {
        return altura;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setKeyPerson(Long keyPerson) {
        this.keyPerson = keyPerson;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put("keyPerson", keyPerson);
        map.put("nombre", nombre);
        map.put("apellido", apellido);
        map.put("fechaNacimiento", fechaNacimiento);
        map.put("cedula", cedula);
        map.put("altura", altura);
        map.put("telefono", telefono);
        map.put("correo", correo);
        map.put("fechaRegistro", fechaRegistro);
        map.put("fechaModificacion", fechaModificacion);

        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(keyPerson, person.keyPerson) &&
        Objects.equals(nombre, person.nombre) &&
        Objects.equals(apellido, person.apellido) &&
        Objects.equals(fechaNacimiento, person.fechaNacimiento) &&
        Objects.equals(cedula, person.cedula) &&
        Objects.equals(altura, person.altura) &&
        Objects.equals(telefono, person.telefono) &&
        Objects.equals(correo, person.correo) &&
        Objects.equals(fechaRegistro, person.fechaRegistro) &&
        Objects.equals(fechaModificacion, person.fechaModificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyPerson, nombre, apellido, fechaNacimiento, cedula,
        altura, telefono, correo, fechaRegistro, fechaModificacion);
    }

    @Override
    public String toString() {
        return "{" +
                "keyPerson=" + keyPerson +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", cedula='" + cedula + '\'' +
                ", altura=" + altura +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

}
