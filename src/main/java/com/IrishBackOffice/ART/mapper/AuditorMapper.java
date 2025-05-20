package com.IrishBackOffice.ART.mapper;

import com.IrishBackOffice.ART.dto.AuditorDTO;
import com.IrishBackOffice.ART.entities.Auditor;
import org.springframework.stereotype.Component;

@Component
public class AuditorMapper {

    public AuditorDTO toDTO(Auditor auditor) {
        if (auditor == null) return null;

        AuditorDTO dto = new AuditorDTO();
        dto.setId(auditor.getId());
        dto.setDni(auditor.getDni());
        dto.setNombre(auditor.getNombre());
        dto.setApellido(auditor.getApellido());
        dto.setCp(auditor.getCp());
        dto.setLocalidad(auditor.getLocalidad());
        dto.setDomicilio(auditor.getDomicilio());
        return dto;
    }

    public Auditor toEntity(AuditorDTO dto) {
        if (dto == null) return null;

        Auditor auditor = new Auditor();
        auditor.setId(dto.getId());
        auditor.setDni(dto.getDni());
        auditor.setNombre(dto.getNombre());
        auditor.setApellido(dto.getApellido());
        auditor.setCp(dto.getCp());
        auditor.setLocalidad(dto.getLocalidad());
        auditor.setDomicilio(dto.getDomicilio());
        return auditor;
    }
}
