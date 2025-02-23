/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.dto;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.enums.Rol;
import java.util.List;

/**
 *
 * @author Pc
 */
public class UsuarioRegistroDTO {

    private Long dni;
    private String nombre;
    private String apellido;
    private Rol rol;
    private String email;
    private String contra;
    private List<Siniestro> siniestros;
    
    

    public UsuarioRegistroDTO() {
    }


    public UsuarioRegistroDTO(Long dni, String nombre, String apellido, Rol rol, String email, String contra, List<Siniestro> siniestros) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
        this.contra = contra;
        this.siniestros = siniestros;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

}
