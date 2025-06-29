/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author Pc
 */
@Entity
public class Asegurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsegurado;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Long cuit;
    @Column(nullable = false)
    private String telefono;
    private String telefono2;
    private String email;
    private String empresa;
    private String nombreFantasia;
    private String domicilio;
    private String localidad;
    private int cp;
    private String prestadorMedico;

    @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactoAsegurado> contactosAsegurado;

   @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Siniestro> siniestros;

    public Asegurado() {
    }

    public Asegurado(Long idAsegurado, String nombre, Long cuit, String telefono, String telefono2, String email, String empresa, String nombreFantasia, String domicilio, String localidad, int cp, String prestadorMedico, List<ContactoAsegurado> contactosAsegurado, List<Siniestro> siniestros) {
        this.idAsegurado = idAsegurado;
        this.nombre = nombre;
        this.cuit = cuit;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.email = email;
        this.empresa = empresa;
        this.nombreFantasia = nombreFantasia;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.cp = cp;
        this.prestadorMedico = prestadorMedico;
        this.contactosAsegurado = contactosAsegurado;
        this.siniestros = siniestros;
    }

    public Long getIdAsegurado() {
        return idAsegurado;
    }

    public void setIdAsegurado(Long idAsegurado) {
        this.idAsegurado = idAsegurado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getPrestadorMedico() {
        return prestadorMedico;
    }

    public void setPrestadorMedico(String prestadorMedico) {
        this.prestadorMedico = prestadorMedico;
    }

    public List<ContactoAsegurado> getContactosAsegurado() {
        return contactosAsegurado;
    }

    public void setContactosAsegurado(List<ContactoAsegurado> contactosAsegurado) {
        this.contactosAsegurado = contactosAsegurado;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

}
