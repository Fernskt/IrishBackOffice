package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Auditor;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.AuditorService;
import com.IrishBackOffice.ART.repositories.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private AuditorRepository auditorRepository;

    @Override
    public Auditor save(Auditor auditor) throws MyException {
        try {
            return auditorRepository.save(auditor);
        } catch (Exception e) {
            throw new MyException("Error al guardar el auditor: " + e.getMessage());
        }
    }

    @Override
    public List<Auditor> listarAuditores() {
        return auditorRepository.findAll();
    }

    @Override
    public Auditor findById(UUID id) throws MyException {
        return auditorRepository.findById(id)
                .orElseThrow(() -> new MyException("Auditor no encontrado con ID: " + id));
    }

    @Override
    public Auditor editarAuditor(Auditor auditor) throws MyException {
        if (auditor.getId() == null || !auditorRepository.existsById(auditor.getId())) {
            throw new MyException("No se encontró el auditor a editar");
        }
        return auditorRepository.save(auditor);
    }

    @Override
    public void eliminarAuditor(UUID id) throws MyException {
        if (!auditorRepository.existsById(id)) {
            throw new MyException("Auditor no encontrado con ID: " + id);
        }
        auditorRepository.deleteById(id);
    }
}
