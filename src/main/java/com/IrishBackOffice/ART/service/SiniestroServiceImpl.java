package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.dto.SiniestroDTO;
import com.IrishBackOffice.ART.entities.Art;
import com.IrishBackOffice.ART.entities.Asegurado;
import com.IrishBackOffice.ART.entities.Auditor;
import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.entities.Trabajador;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import com.IrishBackOffice.ART.repositories.ArtRepository;
import com.IrishBackOffice.ART.repositories.AseguradoRepository;
import com.IrishBackOffice.ART.repositories.AuditorRepository;
import com.IrishBackOffice.ART.repositories.SiniestroRepository;
import com.IrishBackOffice.ART.repositories.TrabajadorRepository;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiniestroServiceImpl implements SiniestroService {

    @Autowired
    private SiniestroRepository siniestroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuditorRepository auditorRepository;

    @Autowired
    private ArtRepository artRepository;

    @Autowired
    private AseguradoRepository aseguradoRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public Siniestro save(SiniestroDTO siniestroDTO) throws MyException {
        // 1. Validaciones
        validaciones(siniestroDTO);

        // 2. Crear la entidad y mapear campos del DTO
        Siniestro siniestro = new Siniestro();
        // Ejemplo de mapeo de campos adicionales:
        siniestro.setNumStro(siniestroDTO.getNumStro());
        siniestro.setResultado(siniestroDTO.getResultado());
        siniestro.setTipoStro(siniestroDTO.getTipoStro());
        siniestro.setFechaYHoraStro(siniestroDTO.getFechaYHoraStro());
        siniestro.setLugar_direccion(siniestroDTO.getLugar_direccion());
        siniestro.setLugar_entrecalles(siniestroDTO.getLugar_entrecalles());
        siniestro.setLocalidad(siniestroDTO.getLocalidad());
        siniestro.setProvincia(siniestroDTO.getProvincia());
        siniestro.setObservaciones(siniestroDTO.getObservaciones());
        siniestro.setMechanicaHecho(siniestroDTO.getMechanicaHecho());
        siniestro.setGravedad(siniestroDTO.getGravedad());
        siniestro.setTipoInvestigacion(siniestroDTO.getTipoInvestigacion());
        siniestro.setNombrePrestadorMedico(siniestroDTO.getNombrePrestadorMedico());
        siniestro.setLesiones(siniestroDTO.getLesiones());
        siniestro.setPatologiasInculpables(siniestroDTO.getPatologiasInculpables());
        siniestro.setRecupero(siniestroDTO.isRecupero());
        siniestro.setEsAceptado(siniestroDTO.isEsAceptado());

        // 3. Fechas de ingreso y vencimiento
        LocalDateTime fechaIngreso = LocalDateTime.now();
        siniestro.setFechaIngreso(fechaIngreso);
        siniestro.setFecha_vencimiento(fechaIngreso.plusDays(10));

        // 4. Asociaciones (Art, Asegurado, Trabajador)
        if (siniestroDTO.getArtId() != null) {
            Art art = artRepository.findById(siniestroDTO.getArtId())
                    .orElseThrow(() -> new MyException("Art no encontrada: " + siniestroDTO.getArtId()));
            siniestro.setArt(art);
        }
        if (siniestroDTO.getAseguradoId() != null) {
            Asegurado asegurado = aseguradoRepository.findById(siniestroDTO.getAseguradoId())
                    .orElseThrow(() -> new MyException("Asegurado no encontrado: " + siniestroDTO.getAseguradoId()));
            siniestro.setAsegurado(asegurado);
        }
        if (siniestroDTO.getTrabajadorId() != null) {
            Trabajador trabajador = trabajadorRepository.findById(siniestroDTO.getTrabajadorId())
                    .orElseThrow(() -> new MyException("Trabajador no encontrado: " + siniestroDTO.getTrabajadorId()));
            siniestro.setTrabajador(trabajador);
        }

        // 5. Guardar y devolver la entidad
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
    public Siniestro editarSiniestro(SiniestroDTO siniestroDTO, Long id) throws MyException {

        Siniestro siniestro = siniestroRepository.findById(id)
                .orElseThrow(() -> new MyException("Siniestro no encontrado con ID: " + id));

        siniestro.setNumStro(siniestroDTO.getNumStro());
        siniestro.setResultado(siniestroDTO.getResultado());
        siniestro.setTipoStro(siniestroDTO.getTipoStro());
        siniestro.setFechaYHoraStro(siniestroDTO.getFechaYHoraStro());
        siniestro.setLugar_direccion(siniestroDTO.getLugar_direccion());
        siniestro.setLugar_entrecalles(siniestroDTO.getLugar_entrecalles());
        siniestro.setLocalidad(siniestroDTO.getLocalidad());
        siniestro.setProvincia(siniestroDTO.getProvincia());
        siniestro.setObservaciones(siniestroDTO.getObservaciones());
        siniestro.setMechanicaHecho(siniestroDTO.getMechanicaHecho());
        siniestro.setGravedad(siniestroDTO.getGravedad());
        siniestro.setTipoInvestigacion(siniestroDTO.getTipoInvestigacion());
        siniestro.setNombrePrestadorMedico(siniestroDTO.getNombrePrestadorMedico());
        siniestro.setLesiones(siniestroDTO.getLesiones());
        siniestro.setPatologiasInculpables(siniestroDTO.getPatologiasInculpables());
        siniestro.setRecupero(siniestroDTO.isRecupero());
        siniestro.setEsAceptado(siniestroDTO.isEsAceptado());

        if (siniestroDTO.getAnalistaId() != null) {
            Usuario analista = usuarioRepository.findById(siniestroDTO.getAnalistaId())
                    .orElseThrow(() -> new MyException("Analista no encontrado: " + siniestroDTO.getAnalistaId()));
            siniestro.setAnalista(analista);
        } else {
            siniestro.setAnalista(null);
        }

        if (siniestroDTO.getAuditorId() != null) {
            Auditor auditor = auditorRepository.findById(siniestroDTO.getAuditorId())
                    .orElseThrow(() -> new MyException("Auditor no encontrado: " + siniestroDTO.getAuditorId()));
            siniestro.setAuditor(auditor);
        } else {
            siniestro.setAuditor(null);
        }
        if (siniestroDTO.getArtId() != null) {
            Art art = artRepository.findById(siniestroDTO.getArtId())
                    .orElseThrow(() -> new MyException("Art no encontrada: " + siniestroDTO.getArtId()));
            siniestro.setArt(art);
        }
        if (siniestroDTO.getAseguradoId() != null) {
            Asegurado asegurado = aseguradoRepository.findById(siniestroDTO.getAseguradoId())
                    .orElseThrow(() -> new MyException("Asegurado no encontrado: " + siniestroDTO.getAseguradoId()));
            siniestro.setAsegurado(asegurado);
        }
        if (siniestroDTO.getTrabajadorId() != null) {
            Trabajador trabajador = trabajadorRepository.findById(siniestroDTO.getTrabajadorId())
                    .orElseThrow(() -> new MyException("Trabajador no encontrado: " + siniestroDTO.getTrabajadorId()));
            siniestro.setTrabajador(trabajador);
        }

        return siniestroRepository.save(siniestro);
    }

    @Override
    public void delete(Siniestro siniestro) throws MyException {

        if (siniestro == null || siniestro.getIdStro() == null) {
            throw new MyException("El siniestro o su ID no pueden ser nulos para eliminar.");
        }
        siniestroRepository.delete(siniestro);
    }

    @Override
    public void asignarAnalista(Long id, UUID analistaId) throws MyException {
        Siniestro sin = siniestroRepository.findById(id)
                .orElseThrow(() -> new MyException("No existe siniestro " + id));
        if (analistaId != null) {
            Usuario u = usuarioRepository.findById(analistaId)
                    .orElseThrow(() -> new MyException("Analista no encontrado " + analistaId));
            sin.setAnalista(u);
        } else {
            sin.setAnalista(null);
        }
        siniestroRepository.save(sin);
    }

    private void validaciones(SiniestroDTO siniestroDTO) throws MyException {
        if (siniestroDTO.getNumStro() == 0) {
            throw new MyException("El número de siniestro no puede ser 0 o estar vacío");
        }
        if (siniestroDTO.getLugar_direccion().isEmpty()) {
            throw new MyException("El lugar del hecho no puede estar vacío");
        }
        if (siniestroDTO.getLugar_entrecalles().isEmpty()) {
            throw new MyException("Complete las entre calles");
        }
        if (siniestroDTO.getLocalidad().isEmpty()) {
            throw new MyException("La localidad no puede estar vacía");
        }
        if (siniestroDTO.getMechanicaHecho().isEmpty()) {
            throw new MyException("La mecánica del hecho no puede estar vacía");
        }
        if (siniestroDTO.getGravedad().equals("0") || siniestroDTO.getGravedad().isEmpty()) {
            throw new MyException("Debe seleccionar la gravedad");
        }
        if (siniestroDTO.getTipoInvestigacion().equalsIgnoreCase("0") || siniestroDTO.getTipoInvestigacion().isEmpty()) {
            throw new MyException("Debe seleccionar el tipo de investigación");
        }
        if (siniestroDTO.getLesiones() == null || siniestroDTO.getLesiones().isEmpty()) {
            throw new MyException("Debe completar las lesiones");
        }
        if (siniestroDTO.getPatologiasInculpables() == null || siniestroDTO.getPatologiasInculpables().isEmpty()) {
            throw new MyException("Debe ingresar las patologías inculpables");
        }
        if (siniestroDTO.getTipoStro() == null || siniestroDTO.getTipoStro().isEmpty()) {
            throw new MyException("Debe ingresar el tipo de siniestro");
        }
    }

}
