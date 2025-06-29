package com.IrishBackOffice.ART.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class AseguradoDTO {

    private Long idAsegurado;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El CUIT es obligatorio")
    private Long cuit;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    private String telefono2;

    @Email(message = "El email no es válido")
    private String email;

    private String empresa;
    
    private String nombreFantasia;
    
    private String domicilio;
    
    private String localidad;
    
    private int cp;

    private String prestadorMedico;
    
     @Valid
    private List<ContactoAseguradoDTO> contactosAsegurado;

    // Getters y Setters

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

    public List<ContactoAseguradoDTO> getContactosAsegurado() {
        return contactosAsegurado;
    }

    public void setContactosAsegurado(List<ContactoAseguradoDTO> contactosAsegurado) {
        this.contactosAsegurado = contactosAsegurado;
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
