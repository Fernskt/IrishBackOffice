package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.AuditorDTO;
import com.IrishBackOffice.ART.entities.Auditor;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.AuditorService;
import com.IrishBackOffice.ART.mapper.AuditorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auditores")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @Autowired
    private AuditorMapper auditorMapper;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<AuditorDTO> lista = auditorService.listarAuditores()
                .stream()
                .map(auditorMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable UUID id) {
        try {
            Auditor auditor = auditorService.findById(id);
            return ResponseEntity.ok(auditorMapper.toDTO(auditor));
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody AuditorDTO dto) {
        try {
            Auditor nuevo = auditorService.save(auditorMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(auditorMapper.toDTO(nuevo));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable UUID id, @Valid @RequestBody AuditorDTO dto) {
        try {
            dto.setId(id);
            Auditor actualizado = auditorService.editarAuditor(auditorMapper.toEntity(dto));
            return ResponseEntity.ok(auditorMapper.toDTO(actualizado));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable UUID id) {
        try {
            auditorService.eliminarAuditor(id);
            return ResponseEntity.noContent().build();
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
