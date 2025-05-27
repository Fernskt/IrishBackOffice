package com.IrishBackOffice.ART.mapper;

import com.IrishBackOffice.ART.dto.*;
import com.IrishBackOffice.ART.entities.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AseguradoMapper {
    
      public ContactoAseguradoDTO toDTO(ContactoAsegurado entity) {
        if (entity == null) return null;
        ContactoAseguradoDTO dto = new ContactoAseguradoDTO();
        dto.setIdContactoAsegurado(entity.getIdContactoAsegurado());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDni(entity.getDni());
        dto.setSector(entity.getSector());
        dto.setTelefono(entity.getTelefono());
        dto.setTelefono2(entity.getTelefono2());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public ContactoAsegurado toEntity(ContactoAseguradoDTO dto) {
        if (dto == null) return null;
        ContactoAsegurado entity = new ContactoAsegurado();
        entity.setIdContactoAsegurado(dto.getIdContactoAsegurado());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDni(dto.getDni());
        entity.setSector(dto.getSector());
        entity.setTelefono(dto.getTelefono());
        entity.setTelefono2(dto.getTelefono2());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public AseguradoDTO toDTO(Asegurado entity) {
        if (entity == null) return null;

        AseguradoDTO dto = new AseguradoDTO();
        dto.setIdAsegurado(entity.getIdAsegurado());
        dto.setNombre(entity.getNombre());
        dto.setCuit(entity.getCuit());
        dto.setTelefono(entity.getTelefono());
        dto.setTelefono2(entity.getTelefono2());
        dto.setEmail(entity.getEmail());
        dto.setEmpresa(entity.getEmpresa());
        dto.setPrestadorMedico(entity.getPrestadorMedico());
         // mapear contactos
        if (entity.getContactosAsegurado() != null) {
            dto.setContactosAsegurado(
                entity.getContactosAsegurado().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Asegurado toEntity(AseguradoDTO dto) {
        if (dto == null) return null;

        Asegurado entity = new Asegurado();
        entity.setIdAsegurado(dto.getIdAsegurado());
        entity.setNombre(dto.getNombre());
        entity.setCuit(dto.getCuit());
        entity.setTelefono(dto.getTelefono());
        entity.setTelefono2(dto.getTelefono2());
        entity.setEmail(dto.getEmail());
        entity.setEmpresa(dto.getEmpresa());
        entity.setPrestadorMedico(dto.getPrestadorMedico());
        // mapear contactos
        if (dto.getContactosAsegurado() != null) {
            entity.setContactosAsegurado(
                dto.getContactosAsegurado().stream()
                    .map(contDto -> {
                        ContactoAsegurado cont = toEntity(contDto);
                        cont.setAsegurado(entity);
                        return cont;
                    })
                    .collect(Collectors.toList())
            );
        }
        return entity;
    }
}
