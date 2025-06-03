package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.SiniestroDTO;
import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siniestros")
public class SiniestroController {

    @Autowired
    private SiniestroService siniestroService;

    /**
     * GET /siniestros
     *
     * Parámetros opcionales: - tipoStro (String) - tipoInvestigacion (String) -
     * resultado (String) - artId (Long)
     *
     * Si no envías ningún parámetro, devuelve todos los siniestros
     * (listarSiniestros).Si envías al menos uno, combina todos los filtros
 (listarPorFiltrosOpcionales).
     * @param tipoStro
     * @param tipoInvestigacion
     * @param resultado
     * @param artId
     * @param analistaId
     * @return 
     */
    @GetMapping
    public ResponseEntity<?> listarSiniestros(
            @RequestParam(required = false) String tipoStro,
            @RequestParam(required = false) String tipoInvestigacion,
            @RequestParam(required = false) String resultado,
            @RequestParam(required = false) Long artId,
            @RequestParam(required = false) UUID analistaId
    ) {
        try {
            // Si todos los filtros están vacíos o en null, traemos la lista completa:
            boolean sinFiltros
                    = (tipoStro == null || tipoStro.trim().isEmpty())
                    && (tipoInvestigacion == null || tipoInvestigacion.trim().isEmpty())
                    && (resultado == null || resultado.trim().isEmpty())
                    && (artId == null)
                    && (analistaId == null);

            if (sinFiltros) {
                // Llama al método original que devuelve todos los registros
                List<Siniestro> todos = siniestroService.listarSiniestros();
                return ResponseEntity.ok(todos);
            }

            // En caso contrario, combinamos los filtros opcionales
            List<Siniestro> filtrados = siniestroService
                    .listarPorFiltrosOpcionales(tipoStro, tipoInvestigacion, resultado, artId, analistaId);
            return ResponseEntity.ok(filtrados);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar o filtrar siniestros");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSiniestro(@PathVariable Long id) {
        try {
            Siniestro siniestro = siniestroService.findById(id);
            if (siniestro == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(siniestro);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el siniestro");
        }
    }

    @PostMapping
    public ResponseEntity<?> crearSiniestro(@RequestBody SiniestroDTO siniestroDTO) {
        try {
            Siniestro nuevoSiniestro = siniestroService.save(siniestroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSiniestro);
        } catch (MyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el siniestro");
        }
    }

    // PUT: Actualizar siniestro
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSiniestro(
            @PathVariable Long id,
            @Valid @RequestBody SiniestroDTO siniestroDTO) {
        try {
            Siniestro siniestroActualizado = siniestroService.editarSiniestro(siniestroDTO, id);
            return ResponseEntity.ok(siniestroActualizado);

        } catch (MyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el siniestro");
        }
    }

    @PatchMapping("/{id}/analista")
    public ResponseEntity<Void> asignarAnalista(
            @PathVariable Long id,
            @RequestBody Map<String, UUID> body
    ) throws MyException {
        UUID analistaId = body.get("analistaId");  // null o UUID
        siniestroService.asignarAnalista(id, analistaId);
        return ResponseEntity.noContent().build();
    }

    // DELETE: Eliminar siniestro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSiniestro(@PathVariable Long id) {
        try {
            Siniestro siniestro = siniestroService.findById(id);
            if (siniestro == null) {
                return ResponseEntity.notFound().build();
            }
            siniestroService.delete(siniestro);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el siniestro");
        }
    }

}
