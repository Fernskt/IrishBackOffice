package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.TrabajadorDTO;
import com.IrishBackOffice.ART.entities.Trabajador;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.TrabajadorService;
import com.IrishBackOffice.ART.mapper.TrabajadorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private TrabajadorMapper mapper;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<TrabajadorDTO> lista = trabajadorService.listarTrabajadores()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable UUID id) {
        try {
            Trabajador trabajador = trabajadorService.findById(id);
            return ResponseEntity.ok(mapper.toDTO(trabajador));
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody TrabajadorDTO dto) {
        try {
            Trabajador nuevo = trabajadorService.save(mapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(nuevo));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable UUID id, @Valid @RequestBody TrabajadorDTO dto) {
        try {
            dto.setId(id);
            Trabajador actualizado = trabajadorService.editarTrabajador(mapper.toEntity(dto));
            return ResponseEntity.ok(mapper.toDTO(actualizado));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable UUID id) {
        try {
            trabajadorService.eliminarTrabajador(id);
            return ResponseEntity.noContent().build();
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
