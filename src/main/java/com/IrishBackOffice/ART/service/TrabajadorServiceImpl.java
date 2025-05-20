package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Trabajador;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.TrabajadorService;
import com.IrishBackOffice.ART.repositories.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public Trabajador save(Trabajador trabajador) throws MyException {
        try {
            return trabajadorRepository.save(trabajador);
        } catch (Exception e) {
            throw new MyException("Error al guardar el trabajador: " + e.getMessage());
        }
    }

    @Override
    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepository.findAll();
    }

    @Override
    public Trabajador findById(UUID id) throws MyException {
        Optional<Trabajador> optional = trabajadorRepository.findById(id);
        return optional.orElseThrow(() -> new MyException("Trabajador no encontrado con ID: " + id));
    }

    @Override
    public Trabajador editarTrabajador(Trabajador trabajador) throws MyException {
        if (trabajador.getId() == null || !trabajadorRepository.existsById(trabajador.getId())) {
            throw new MyException("No se encontró el trabajador a editar");
        }
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public void eliminarTrabajador(UUID id) throws MyException {
        if (!trabajadorRepository.existsById(id)) {
            throw new MyException("Trabajador no encontrado con ID: " + id);
        }
        trabajadorRepository.deleteById(id);
    }
}
