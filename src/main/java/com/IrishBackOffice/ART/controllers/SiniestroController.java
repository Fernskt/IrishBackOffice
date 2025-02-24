package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/siniestros")
public class SiniestroController {

    @Autowired
    private SiniestroService siniestroService;

    // GET: Listar siniestros
    @GetMapping("/listar")
    public ResponseEntity<?> listarSiniestros() {
        try {
            return ResponseEntity.ok(siniestroService.listarSiniestros());
        } catch (Exception e) {
            // Manejo de errores genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al listar siniestros");
        }
    }

    // GET: Obtener siniestro por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSiniestro(@PathVariable Long id) {
        try {
            Siniestro siniestro = siniestroService.findById(id);
            if (siniestro == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(siniestro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al obtener el siniestro");
        }
    }

    // POST: Crear siniestro
    @PostMapping
    public ResponseEntity<?> crearSiniestro(@RequestBody Siniestro siniestro) {
        try {
            Siniestro nuevoSiniestro = siniestroService.save(siniestro);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSiniestro);
        } catch (MyException ex) {
            // Si tu excepción MyException tiene un mensaje específico, puedes retornarlo
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear el siniestro");
        }
    }

    // PUT: Actualizar siniestro
   /* @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSiniestro(
            @PathVariable Long id,
            @RequestBody Siniestro siniestroActualizado) {
        try {
            Siniestro siniestroDB = siniestroService.findById(id);
            if (siniestroDB == null) {
                return ResponseEntity.notFound().build();
            }
            // Ajustas manualmente los campos que se pueden actualizar
            siniestroDB.setDescripcion(siniestroActualizado.getDescripcion());
            siniestroDB.setFecha(siniestroActualizado.getFecha());
            // etc...

            Siniestro siniestroGuardado = siniestroService.save(siniestroDB);
            return ResponseEntity.ok(siniestroGuardado);
        } catch (MyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar el siniestro");
        }
    }*/

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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al eliminar el siniestro");
        }
    } 
}
