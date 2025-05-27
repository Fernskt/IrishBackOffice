/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

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
     @OneToMany(mappedBy = "trabajador")
    @JsonIgnore
    private List<Siniestro> siniestros;

    public Trabajador() {
    }

    public Trabajador(String telefono, String telefono2, String email, String calle, int numero, String piso, String depto, int cp, String localidad, String provincia, List<Siniestro> siniestros, Long dni, String nombre, String apellido) {
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
        this.siniestros = siniestros;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getEmail() {
        return email;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getPiso() {
        return piso;
    }

    public String getDepto() {
        return depto;
    }

    public int getCp() {
        return cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }

   
}
