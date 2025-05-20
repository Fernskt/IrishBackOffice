package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Art;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.ArtService;
import com.IrishBackOffice.ART.repositories.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtServiceImpl implements ArtService {

    @Autowired
    private ArtRepository artRepository;

    @Override
    public Art save(Art art) throws MyException {
        try {
            return artRepository.save(art);
        } catch (Exception e) {
            throw new MyException("Error al guardar la ART: " + e.getMessage());
        }
    }

    @Override
    public List<Art> listarARTs() {
        return artRepository.findAll();
    }

    @Override
    public Art findById(Long id) throws MyException {
        return artRepository.findById(id)
                .orElseThrow(() -> new MyException("ART no encontrada con ID: " + id));
    }

    @Override
    public Art editarArt(Art art) throws MyException {
        if (art.getIdART() == null || !artRepository.existsById(art.getIdART())) {
            throw new MyException("No se encontró la ART a editar");
        }
        return artRepository.save(art);
    }

    @Override
    public void eliminarArt(Long id) throws MyException {
        if (!artRepository.existsById(id)) {
            throw new MyException("ART no encontrada con ID: " + id);
        }
        artRepository.deleteById(id);
    }
}
