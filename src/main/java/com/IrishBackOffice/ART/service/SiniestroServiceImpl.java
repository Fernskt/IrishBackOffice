package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import com.IrishBackOffice.ART.repositories.SiniestroRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiniestroServiceImpl implements SiniestroService {

    @Autowired
    private SiniestroRepository siniestroRepository;

    @Override
    public Siniestro save(Siniestro siniestro) throws MyException {
        validaciones(siniestro);

        LocalDateTime fechaIngreso = LocalDateTime.now();
        siniestro.setFechaIngreso(fechaIngreso);
        siniestro.setFecha_vencimiento(fechaIngreso.plusDays(10));

        return siniestroRepository.save(siniestro);
    }

    @Override
    public List<Siniestro> listarSiniestros() {
        return siniestroRepository.findAll();
    }

    @Override
    public Siniestro findById(Long id) throws MyException {
        
        return siniestroRepository.findById(id)
                .orElseThrow(() -> new MyException("Siniestro no encontrado con ID: " + id));
    }

    @Override
    public void delete(Siniestro siniestro) throws MyException {
       
        if (siniestro == null || siniestro.getIdStro() == null) {
            throw new MyException("El siniestro o su ID no pueden ser nulos para eliminar.");
        }
        siniestroRepository.delete(siniestro);
    }

    private void validaciones(Siniestro siniestro) throws MyException {
        if (siniestro.getNumStro() == 0) {
            throw new MyException("El número de siniestro no puede ser 0 o estar vacío");
        }
        if (siniestro.getLugar_direccion().isEmpty()) {
            throw new MyException("El lugar del hecho no puede estar vacío");
        }
        if (siniestro.getLugar_entrecalles().isEmpty()) {
            throw new MyException("Complete las entre calles");
        }
        if (siniestro.getLocalidad().isEmpty()) {
            throw new MyException("La localidad no puede estar vacía");
        }
        if (siniestro.getMechanicaHecho().isEmpty()) {
            throw new MyException("La mecánica del hecho no puede estar vacía");
        }
        if (siniestro.getGravedad().equals("0") || siniestro.getGravedad().isEmpty()) {
            throw new MyException("Debe seleccionar la gravedad");
        }
        if (siniestro.getTipoInvestigacion().equalsIgnoreCase("0") || siniestro.getTipoInvestigacion().isEmpty()) {
            throw new MyException("Debe seleccionar el tipo de investigación");
        }
        if (siniestro.getLesiones() == null || siniestro.getLesiones().isEmpty()) {
            throw new MyException("Debe completar las lesiones");
        }
        if (siniestro.getPatologiasInculpables() == null || siniestro.getPatologiasInculpables().isEmpty()) {
            throw new MyException("Debe ingresar las patologías inculpables");
        }
        if (siniestro.getTipoStro() == null || siniestro.getTipoStro().isEmpty()) {
            throw new MyException("Debe ingresar el tipo de siniestro");
        }
    }
}
