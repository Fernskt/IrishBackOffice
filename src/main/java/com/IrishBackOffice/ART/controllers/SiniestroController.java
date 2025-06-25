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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     * (listarPorFiltrosOpcionales).
     *
     * @param tipoStro
     * @param tipoInvestigacion
     * @param resultado
     * @param artId
     * @param analistaId
     * @param page
     * @param size
     * @param sortDir
     * @return
     */
    @GetMapping
    public ResponseEntity<?> listarSiniestros(
            @RequestParam(required = false) String tipoStro,
            @RequestParam(required = false) String tipoInvestigacion,
            @RequestParam(required = false) String resultado,
            @RequestParam(required = false) Long artId,
            @RequestParam(required = false) UUID analistaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        try {
            Sort sort = Sort.by("fechaIngreso");
            sort = "desc".equalsIgnoreCase(sortDir) ? sort.descending() : sort.ascending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Siniestro> resultadoPage = siniestroService
                    .listarPorFiltrosOpcionales(
                            tipoStro,
                            tipoInvestigacion,
                            resultado,
                            artId,
                            analistaId,
                            pageable
                    );

            return ResponseEntity.ok(resultadoPage);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar siniestros");
        }
    }

    @GetMapping("/numero/{numStro}")
    public ResponseEntity<?> obtenerPorNumStro(@PathVariable int numStro) {
        try {
            Siniestro sin = siniestroService.findByNumStro(numStro);
            return ResponseEntity.ok(sin);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró siniestro con número: " + numStro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar siniestro por numStro");
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
