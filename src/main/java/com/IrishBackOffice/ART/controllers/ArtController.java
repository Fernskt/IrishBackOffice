package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.ArtDTO;
import com.IrishBackOffice.ART.entities.Art;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.ArtService;
import com.IrishBackOffice.ART.mapper.ArtMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/arts")
public class ArtController {

    @Autowired
    private ArtService artService;

    @Autowired
    private ArtMapper mapper;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<ArtDTO> lista = artService.listarARTs()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            Art art = artService.findById(id);
            return ResponseEntity.ok(mapper.toDTO(art));
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ArtDTO dto) {
        try {
            Art nueva = artService.save(mapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(nueva));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ArtDTO dto) {
        try {
            dto.setIdART(id);
            Art actualizada = artService.editarArt(mapper.toEntity(dto));
            return ResponseEntity.ok(mapper.toDTO(actualizada));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            artService.eliminarArt(id);
            return ResponseEntity.noContent().build();
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
