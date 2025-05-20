package com.IrishBackOffice.ART.mapper;

import com.IrishBackOffice.ART.dto.TrabajadorDTO;
import com.IrishBackOffice.ART.entities.Trabajador;
import org.springframework.stereotype.Component;

@Component
public class TrabajadorMapper {

    public TrabajadorDTO toDTO(Trabajador entity) {
        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setId(entity.getId());
        dto.setDni(entity.getDni());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setTelefono(entity.getTelefono());
        dto.setTelefono2(entity.getTelefono2());
        dto.setEmail(entity.getEmail());
        dto.setCalle(entity.getCalle());
        dto.setNumero(entity.getNumero());
        dto.setPiso(entity.getPiso());
        dto.setDepto(entity.getDepto());
        dto.setCp(entity.getCp());
        dto.setLocalidad(entity.getLocalidad());
        dto.setProvincia(entity.getProvincia());
        return dto;
    }

    public Trabajador toEntity(TrabajadorDTO dto) {
        Trabajador entity = new Trabajador();
        entity.setId(dto.getId());
        entity.setDni(dto.getDni());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setTelefono2(dto.getTelefono2());
        entity.setEmail(dto.getEmail());
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setPiso(dto.getPiso());
        entity.setDepto(dto.getDepto());
        entity.setCp(dto.getCp() != null ? dto.getCp() : 0);
        entity.setLocalidad(dto.getLocalidad());
        entity.setProvincia(dto.getProvincia());
        return entity;
    }
}
