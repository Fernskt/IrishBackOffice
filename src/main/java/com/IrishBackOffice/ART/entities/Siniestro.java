/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 *
 * @author Pc
 */
@Entity
public class Siniestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStro;
    @Column(nullable = false, unique = true)
    private int numStro;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fecha_vencimiento;  
    private String resultado;  
    private String tipoStro;
   @Column(name = "fecha_y_hora", nullable = false)
    private LocalDateTime fechaYHoraStro;
    @Column(nullable = false)
    private String lugar_direccion;
    private String lugar_entrecalles;
    @Column(nullable = false)
    private String localidad;
    private String provincia;
    private String observaciones;
    private String mechanicaHecho;
    private String gravedad;
    private String tipoInvestigacion;
    private String nombrePrestadorMedico;
    private String lesiones;
    private String patologiasInculpables;
    private String estado;
    private boolean recupero;
    private boolean esAceptado;
     @ManyToOne
     @JoinColumn(name = "idAnalista")
    private Usuario analista;
    @ManyToOne
    @JoinColumn(name = "idART")
    private Art art;
    @ManyToOne
     @JoinColumn(name = "idAuditor")
    private Auditor auditor;
    @ManyToOne
    @JoinColumn(name = "idAsegurado")
    @JsonBackReference
    private Asegurado asegurado;
    @ManyToOne
     @JoinColumn(name = "idTrabajador")
    private Trabajador trabajador;

    public Siniestro() {
    }

    public Siniestro(Long idStro, int numStro, LocalDateTime fechaIngreso, LocalDateTime fecha_vencimiento, String resultado, String tipoStro, LocalDateTime fechaYHoraStro, String lugar_direccion, String lugar_entrecalles, String localidad, String provincia, String observaciones, String mechanicaHecho, String gravedad, String tipoInvestigacion, String nombrePrestadorMedico, String lesiones, String patologiasInculpables, String estado, boolean recupero, boolean esAceptado, Usuario analista, Art art, Auditor auditor, Asegurado asegurado, Trabajador trabajador) {
        this.idStro = idStro;
        this.numStro = numStro;
        this.fechaIngreso = fechaIngreso;
        this.fecha_vencimiento = fecha_vencimiento;
        this.resultado = resultado;
        this.tipoStro = tipoStro;
        this.fechaYHoraStro = fechaYHoraStro;
        this.lugar_direccion = lugar_direccion;
        this.lugar_entrecalles = lugar_entrecalles;
        this.localidad = localidad;
        this.provincia = provincia;
        this.observaciones = observaciones;
        this.mechanicaHecho = mechanicaHecho;
        this.gravedad = gravedad;
        this.tipoInvestigacion = tipoInvestigacion;
        this.nombrePrestadorMedico = nombrePrestadorMedico;
        this.lesiones = lesiones;
        this.patologiasInculpables = patologiasInculpables;
        this.estado = estado;
        this.recupero = recupero;
        this.esAceptado = esAceptado;
        this.analista = analista;
        this.art = art;
        this.auditor = auditor;
        this.asegurado = asegurado;
        this.trabajador = trabajador;
    }


    public Usuario getAnalista() {
        return analista;
    }

    public void setAnalista(Usuario analista) {
        this.analista = analista;
    }

    public LocalDateTime getFechaYHoraStro() {
        return fechaYHoraStro;
    }

    public void setFechaYHoraStro(LocalDateTime fechaYHoraStro) {
        this.fechaYHoraStro = fechaYHoraStro;
    }

    public String getLugar_direccion() {
        return lugar_direccion;
    }

    public void setLugar_direccion(String lugar_direccion) {
        this.lugar_direccion = lugar_direccion;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public boolean isRecupero() {
        return recupero;
    }

    public void setRecupero(boolean recupero) {
        this.recupero = recupero;
    }

    public boolean isEsAceptado() {
        return esAceptado;
    }

    public void setEsAceptado(boolean esAceptado) {
        this.esAceptado = esAceptado;
    }

    public Art getArt() {
        return art;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    public Long getIdStro() {
        return idStro;
    }

    public void setIdStro(Long idStro) {
        this.idStro = idStro;
    }

    public int getNumStro() {
        return numStro;
    }

    public void setNumStro(int numStro) {
        this.numStro = numStro;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDateTime fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }


    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipoStro() {
        return tipoStro;
    }

    public void setTipoStro(String tipoStro) {
        this.tipoStro = tipoStro;
    }

    public String getLugar_entrecalles() {
        return lugar_entrecalles;
    }

    public void setLugar_entrecalles(String lugar_entrecalles) {
        this.lugar_entrecalles = lugar_entrecalles;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMechanicaHecho() {
        return mechanicaHecho;
    }

    public void setMechanicaHecho(String mechanicaHecho) {
        this.mechanicaHecho = mechanicaHecho;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getTipoInvestigacion() {
        return tipoInvestigacion;
    }

    public void setTipoInvestigacion(String tipoInvestigacion) {
        this.tipoInvestigacion = tipoInvestigacion;
    }

    public String getNombrePrestadorMedico() {
        return nombrePrestadorMedico;
    }

    public void setNombrePrestadorMedico(String nombrePrestadorMedico) {
        this.nombrePrestadorMedico = nombrePrestadorMedico;
    }

    public String getLesiones() {
        return lesiones;
    }

    public void setLesiones(String lesiones) {
        this.lesiones = lesiones;
    }

    public String getPatologiasInculpables() {
        return patologiasInculpables;
    }

    public void setPatologiasInculpables(String patologiasInculpables) {
        this.patologiasInculpables = patologiasInculpables;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
