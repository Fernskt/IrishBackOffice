package com.IrishBackOffice.ART.dto;

import jakarta.validation.constraints.*;

public class ContactoAseguradoDTO {

    private Long idContactoAsegurado;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    private Long dni;

    private String sector;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    private String telefono2;

    @Email(message = "El email no es válido")
    private String email;
    
    private AseguradoMiniDTO asegurado;

    // getters / setters

    public Long getIdContactoAsegurado() {
        return idContactoAsegurado;
    }
    public void setIdContactoAsegurado(Long idContactoAsegurado) {
        this.idContactoAsegurado = idContactoAsegurado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
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

    public AseguradoMiniDTO getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(AseguradoMiniDTO asegurado) {
        this.asegurado = asegurado;
    }
    
    
}
