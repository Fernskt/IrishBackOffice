package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Asegurado;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.IAseguradoService;
import com.IrishBackOffice.ART.repositories.AseguradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AseguradoServiceImpl implements IAseguradoService {

    @Autowired
    private AseguradoRepository aseguradoRepository;

    @Override
    public Asegurado save(Asegurado asegurado) throws MyException {
        try {
            // Validaciones si es necesario
            return aseguradoRepository.save(asegurado);
        } catch (Exception e) {
            throw new MyException("Error al guardar el asegurado: " + e.getMessage());
        }
    }

    @Override
    public List<Asegurado> listarAsegurados() {
        return aseguradoRepository.findAll();
    }

    @Override
    public Asegurado findById(Long id) throws MyException {
        Optional<Asegurado> optional = aseguradoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new MyException("No se encontró un asegurado con ID: " + id);
        }
    }

    @Override
    public Asegurado editarAsegurado(Asegurado asegurado) throws MyException {
        if (asegurado.getIdAsegurado() == null) {
            throw new MyException("El ID del asegurado no puede ser nulo");
        }

        Optional<Asegurado> existente = aseguradoRepository.findById(asegurado.getIdAsegurado());
        if (existente.isEmpty()) {
            throw new MyException("No existe un asegurado con ID: " + asegurado.getIdAsegurado());
        }

        try {
            return aseguradoRepository.save(asegurado);
        } catch (Exception e) {
            throw new MyException("Error al editar el asegurado: " + e.getMessage());
        }
    }

    @Override
    public void eliminarAsegurado(Long id) throws MyException {
        if (!aseguradoRepository.existsById(id)) {
            throw new MyException("No existe un asegurado con ID: " + id);
        }

        try {
            aseguradoRepository.deleteById(id);
        } catch (Exception e) {
            throw new MyException("Error al eliminar el asegurado: " + e.getMessage());
        }
    }
}
