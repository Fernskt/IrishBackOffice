package com.IrishBackOffice.ART.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author Pc
 */
public class SiniestroDTO {
    
    @NotNull
    private int numStro;
    private String resultado;
    @NotBlank
    private String tipoStro;
 
    private LocalDateTime fechaYHoraStro;
    
    @NotBlank
    private String lugar_direccion;
    
    private String lugar_entrecalles;
    @NotBlank
    private String localidad;
    @NotBlank
    private String provincia;
    private String observaciones;
    @NotBlank
    private String mechanicaHecho;
    @NotBlank
    private String gravedad;
    @NotBlank
    private String tipoInvestigacion;
    @NotBlank
    private String nombrePrestadorMedico;
    @NotBlank
    private String lesiones;
    private String patologiasInculpables;
    private boolean recupero;
    private boolean esAceptado;

    private UUID analistaId;
    private UUID auditorId;
    private Long artId;
    private Long aseguradoId;
    private UUID trabajadorId;

    public SiniestroDTO() {
    }

    public SiniestroDTO(int numStro, String resultado, String tipoStro, LocalDateTime fechaYHoraStro, String lugar_direccion, String lugar_entrecalles, String localidad, String provincia, String observaciones, String mechanicaHecho, String gravedad, String tipoInvestigacion, String nombrePrestadorMedico, String lesiones, String patologiasInculpables, boolean recupero, boolean esAceptado, UUID analistaId, UUID auditorId, Long artId, Long aseguradoId, UUID trabajadorId) {
        this.numStro = numStro;
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
        this.recupero = recupero;
        this.esAceptado = esAceptado;
        this.analistaId = analistaId;
        this.auditorId = auditorId;
        this.artId = artId;
        this.aseguradoId = aseguradoId;
        this.trabajadorId = trabajadorId;
    }

    public int getNumStro() {
        return numStro;
    }

    public String getResultado() {
        return resultado;
    }

    public String getTipoStro() {
        return tipoStro;
    }

    public LocalDateTime getFechaYHoraStro() {
        return fechaYHoraStro;
    }

    public String getLugar_direccion() {
        return lugar_direccion;
    }

    public String getLugar_entrecalles() {
        return lugar_entrecalles;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getMechanicaHecho() {
        return mechanicaHecho;
    }

    public String getGravedad() {
        return gravedad;
    }

    public String getTipoInvestigacion() {
        return tipoInvestigacion;
    }

    public String getNombrePrestadorMedico() {
        return nombrePrestadorMedico;
    }

    public String getLesiones() {
        return lesiones;
    }

    public String getPatologiasInculpables() {
        return patologiasInculpables;
    }

    public boolean isRecupero() {
        return recupero;
    }

    public boolean isEsAceptado() {
        return esAceptado;
    }

    public UUID getAnalistaId() {
        return analistaId;
    }

    public UUID getAuditorId() {
        return auditorId;
    }

    public Long getArtId() {
        return artId;
    }

    public Long getAseguradoId() {
        return aseguradoId;
    }

    public UUID getTrabajadorId() {
        return trabajadorId;
    }
    
}