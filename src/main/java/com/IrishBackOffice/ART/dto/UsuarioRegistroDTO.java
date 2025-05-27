/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.dto;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author Pc
 */
public class UsuarioRegistroDTO {

@NotNull(message = "El DNI es obligatorio")
    private Long dni;
    
    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotEmpty(message = "El apellido es obligatorio")
    private String apellido;
    
    @NotNull(message = "El rol no puede ser nulo")
    private Rol rol;
    
    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;
    
    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
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
