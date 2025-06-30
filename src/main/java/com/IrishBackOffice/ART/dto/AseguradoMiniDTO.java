// AseguradoMiniDTO.java
package com.IrishBackOffice.ART.dto;

public class AseguradoMiniDTO {
    private Long idAsegurado;
    private String nombre;
    private String empresa;

    public AseguradoMiniDTO() {}

    public AseguradoMiniDTO(Long idAsegurado, String nombre, String empresa) {
        this.idAsegurado = idAsegurado;
        this.nombre = nombre;
        this.empresa = empresa;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
