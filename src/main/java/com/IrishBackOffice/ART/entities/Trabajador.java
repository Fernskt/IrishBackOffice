/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 *
 * @author Pc
 */
@Entity
public class Trabajador extends Persona {
    
    private String telefono;
    private String telefono2;
    private String email;
    private String calle;
    private int numero;
    private String piso;
    private String depto;
    private int cp;
    private String localidad;
    private String provincia;
    @OneToOne(mappedBy = "trabajador")
    private Siniestro siniestro;


    public Trabajador() {
    }

    public Trabajador(String telefono, String telefono2, String email, String calle, int numero, String piso, String depto, int cp, String localidad, String provincia, Siniestro siniestro, Long dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.depto = depto;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.siniestro = siniestro;
    }

    public Siniestro getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(Siniestro siniestro) {
        this.siniestro = siniestro;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    
}
