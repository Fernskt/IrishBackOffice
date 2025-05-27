package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.AseguradoDTO;
import com.IrishBackOffice.ART.entities.Asegurado;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.IAseguradoService;
import com.IrishBackOffice.ART.mapper.AseguradoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asegurados")
public class AseguradoController {

    @Autowired
    private IAseguradoService aseguradoService;

    @Autowired
    private AseguradoMapper mapper;

    @GetMapping
    public ResponseEntity<?> listarAsegurados() {
        try {
            List<AseguradoDTO> lista = aseguradoService.listarAsegurados()
                    .stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar asegurados");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAsegurado(@PathVariable Long id) {
        try {
            Asegurado asegurado = aseguradoService.findById(id);
            return ResponseEntity.ok(mapper.toDTO(asegurado));
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearAsegurado(@Valid @RequestBody AseguradoDTO dto) {
        try {
            Asegurado nuevo = aseguradoService.save(mapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(nuevo));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAsegurado(@PathVariable Long id, @Valid @RequestBody AseguradoDTO dto) {
        try {
            dto.setIdAsegurado(id);
            Asegurado actualizado = aseguradoService.editarAsegurado(mapper.toEntity(dto));
            return ResponseEntity.ok(mapper.toDTO(actualizado));
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsegurado(@PathVariable Long id) {
        try {
            aseguradoService.eliminarAsegurado(id);
            return ResponseEntity.noContent().build();
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
