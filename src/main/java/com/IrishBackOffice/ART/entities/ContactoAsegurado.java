/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Pc
 */
@Entity
public class ContactoAsegurado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContactoAsegurado;
    @Column(nullable = false)
     private String nombre;
    @Column(nullable = false)
     private String apellido;
     private Long dni;
     private String sector;
     @Column(nullable = false)
     private String telefono;
     private String telefono2;
     @Column(nullable = false)
     private String email;
     
    @ManyToOne
    @JoinColumn(name="idAsegurado") 
    @JsonIgnore
    private Asegurado asegurado;

    public ContactoAsegurado() {
    }

    public ContactoAsegurado(Long idContactoAsegurado, String nombre, String apellido, Long dni, String sector, String telefono, String telefono2, String email, Asegurado asegurado) {
        this.idContactoAsegurado = idContactoAsegurado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sector = sector;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.email = email;
        this.asegurado = asegurado;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }


    public Long getIdContactoAsegurado() {
        return idContactoAsegurado;
    }

    public void setIdContactoAsegurado(Long idContactoAsegurado) {
        this.idContactoAsegurado = idContactoAsegurado;
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

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
}
