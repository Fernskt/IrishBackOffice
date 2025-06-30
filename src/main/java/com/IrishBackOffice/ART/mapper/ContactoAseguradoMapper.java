package com.IrishBackOffice.ART.mapper;

import com.IrishBackOffice.ART.dto.AseguradoMiniDTO;
import com.IrishBackOffice.ART.dto.ContactoAseguradoDTO;
import com.IrishBackOffice.ART.entities.Asegurado;
import com.IrishBackOffice.ART.entities.ContactoAsegurado;
import org.springframework.stereotype.Component;

@Component
public class ContactoAseguradoMapper {

    /**
     * Mapea de entidad a DTO, incluyendo el mini-ASEGURADO anidado.
     * @param e
     * @return 
     */
    public ContactoAseguradoDTO toDTO(ContactoAsegurado e) {
        if (e == null) return null;

        ContactoAseguradoDTO dto = new ContactoAseguradoDTO();
        dto.setIdContactoAsegurado(e.getIdContactoAsegurado());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setDni(e.getDni());
        dto.setSector(e.getSector());
        dto.setTelefono(e.getTelefono());
        dto.setTelefono2(e.getTelefono2());
        dto.setEmail(e.getEmail());

        if (e.getAsegurado() != null) {
            AseguradoMiniDTO mini = new AseguradoMiniDTO();
            mini.setIdAsegurado(e.getAsegurado().getIdAsegurado());
            mini.setNombre(e.getAsegurado().getNombre());
            mini.setEmpresa(e.getAsegurado().getEmpresa());
            dto.setAsegurado(mini);
        }

        return dto;
    }

    /**
     * Mapea del DTO a la entidad.Si el DTO trae mini-ASEGURADO,
 crea una instancia de Asegurado con solo el ID seteado (para asociación).
     * @param dto
     * @return 
     */
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

        if (dto.getAsegurado() != null) {
            // Asociamos solo por ID; JPA la resolverá al guardar
            Asegurado aseg = new Asegurado();
            aseg.setIdAsegurado(dto.getAsegurado().getIdAsegurado());
            entity.setAsegurado(aseg);
        }

        return entity;
    }
}
