/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import com.IrishBackOffice.ART.enums.Rol;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author Pc
 */
@Entity
public class Usuario extends Persona {

    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String contra;
    @OneToMany(mappedBy = "analista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Siniestro> siniestros;

    public Usuario() {
        // Constructor por defecto requerido por JPA
    }

    public Usuario(Rol rol, String email, String contra, List<Siniestro> siniestros) {
        this.rol = rol;
        this.email = email;
        this.contra = contra;
        this.siniestros = siniestros;
    }

    public Usuario(Rol rol, String email, String contra, List<Siniestro> siniestros, Long dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
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
