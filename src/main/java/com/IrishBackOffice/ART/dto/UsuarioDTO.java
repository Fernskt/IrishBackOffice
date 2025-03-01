/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.dto;

import com.IrishBackOffice.ART.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Pc
 */
public class UsuarioDTO {

   @NotNull(message = "El DNI es obligatorio")
   @Positive(message = "Debe ser mayor que 0")
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

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long dni, String nombre, String apellido, Rol rol, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
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

       
}
