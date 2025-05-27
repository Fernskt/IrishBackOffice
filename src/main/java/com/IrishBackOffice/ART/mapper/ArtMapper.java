package com.IrishBackOffice.ART.mapper;

import com.IrishBackOffice.ART.dto.ArtDTO;
import com.IrishBackOffice.ART.entities.Art;
import org.springframework.stereotype.Component;

@Component
public class ArtMapper {

    public ArtDTO toDTO(Art entity) {
        ArtDTO dto = new ArtDTO();
        dto.setIdART(entity.getIdART());
        dto.setNombreART(entity.getNombreART());
        dto.setNombreAnalista(entity.getNombreAnalista());
        dto.setApellidoAnalista(entity.getApellidoAnalista());
        return dto;
    }

    public Art toEntity(ArtDTO dto) {
        Art entity = new Art();
        entity.setIdART(dto.getIdART());
        entity.setNombreART(dto.getNombreART());
        entity.setNombreAnalista(dto.getNombreAnalista());
        entity.setApellidoAnalista(dto.getApellidoAnalista());
        return entity;
    }
}
