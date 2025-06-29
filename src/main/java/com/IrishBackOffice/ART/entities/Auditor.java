/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author Pc
 */
@Entity
public class Auditor extends Persona {
    
    @Column(nullable = false)
    private int cp;
    
     @Column(nullable = false)
    private String localidad;
    private String domicilio;
    private String telefono;
    private String telefono2;
    
    @OneToMany(mappedBy = "auditor")
    private List<Siniestro> siniestros;

    public Auditor() {
    }

    public Auditor(int cp, String localidad, String domicilio, String telefono, String telefono2, List<Siniestro> siniestros, Long dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
        this.cp = cp;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.siniestros = siniestros;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
    
    
}
