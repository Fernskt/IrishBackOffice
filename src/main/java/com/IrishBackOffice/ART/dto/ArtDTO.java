package com.IrishBackOffice.ART.dto;

import jakarta.validation.constraints.*;

public class ArtDTO {

    private Long idART;

    @NotBlank(message = "El nombre de la ART es obligatorio")
    private String nombreART;

    @NotBlank(message = "El nombre del analista es obligatorio")
    private String nombreAnalista;

    @NotBlank(message = "El apellido del analista es obligatorio")
    private String apellidoAnalista;

    // Getters y Setters

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
}
