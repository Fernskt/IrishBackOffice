package com.IrishBackOffice.ART.controllers;
import com.IrishBackOffice.ART.dto.SiniestroDTO;
import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import jakarta.validation.Valid;
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

    
    @GetMapping()
    public ResponseEntity<?> listarSiniestros() {
        try {
            return ResponseEntity.ok(siniestroService.listarSiniestros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al listar siniestros");
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
    public ResponseEntity<?> crearSiniestro(@RequestBody Siniestro siniestro) {
        try {
            Siniestro nuevoSiniestro = siniestroService.save(siniestro);
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
