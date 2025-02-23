/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;


import jakarta.persistence.Entity;
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
public class Art {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idART;
     private String nombreART;
     private String nombreAnalista;
     private String apellidoAnalista;
     
     @OneToMany(mappedBy = "art")
    private List<Siniestro> siniestros;

    public Art() {
    }

    public Art(Long idART, String nombreART, String nombreAnalista, String apellidoAnalista, List<Siniestro> siniestros) {
        this.idART = idART;
        this.nombreART = nombreART;
        this.nombreAnalista = nombreAnalista;
        this.apellidoAnalista = apellidoAnalista;
        this.siniestros = siniestros;
    }

    public String getNombreAnalista() {
        return nombreAnalista;
    }

    public void setNombreAnalista(String nombreAnalista) {
        this.nombreAnalista = nombreAnalista;
    }

    public String getApellidoAnalista() {
        return apellidoAnalista;
    }

    public void setApellidoAnalista(String apellidoAnalista) {
        this.apellidoAnalista = apellidoAnalista;
    }

    
    
    public Long getIdART() {
        return idART;
    }

    public void setIdART(Long idART) {
        this.idART = idART;
    }

    public String getNombreART() {
        return nombreART;
    }

    public void setNombreART(String nombreART) {
        this.nombreART = nombreART;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
     
     
}
