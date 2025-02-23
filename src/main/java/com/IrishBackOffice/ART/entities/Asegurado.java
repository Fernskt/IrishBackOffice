/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private String prestadorMedico;
    
    @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactoAsegurado> contactosAsegurado;
    
    @OneToOne
    @JoinColumn(name = "idStro")
    private Siniestro stro;

    public Asegurado() {
    }

    public Asegurado(Long idAsegurado, String nombre, Long cuit, String telefono, String telefono2, String email, String empresa, String prestadorMedico, List<ContactoAsegurado> contactosAsegurado, Siniestro stro) {
        this.idAsegurado = idAsegurado;
        this.nombre = nombre;
        this.cuit = cuit;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.email = email;
        this.empresa = empresa;
        this.prestadorMedico = prestadorMedico;
        this.contactosAsegurado = contactosAsegurado;
        this.stro = stro;
    }

    public List<ContactoAsegurado> getContactosAsegurado() {
        return contactosAsegurado;
    }

    public void setContactosAsegurado(List<ContactoAsegurado> contactosAsegurado) {
        this.contactosAsegurado = contactosAsegurado;
    }

    

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
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

    public String getPrestadorMedico() {
        return prestadorMedico;
    }

    public void setPrestadorMedico(String prestadorMedico) {
        this.prestadorMedico = prestadorMedico;
    }

    public Siniestro getStro() {
        return stro;
    }

    public void setStro(Siniestro stro) {
        this.stro = stro;
    }
    
    
    
}
