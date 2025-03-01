package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.UsuarioDTO;
import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.enums.Rol;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.UsuarioService;
import jakarta.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pc
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuario() {
        try {
            return ResponseEntity.ok(usuarioService.listarUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar a los usuarios");
        }
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @ModelAttribute("roles")
    public Rol[] getRoles() {
        return Rol.values();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        try {
            usuarioService.save(usuarioRegistroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("El registro ha sido exitoso!");
        } catch (MyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable UUID id,
            @RequestBody @Valid UsuarioDTO usuarioDetails,
            BindingResult result) {
        
        if (result.hasErrors()) {
            String errores = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            Usuario usuarioActualizado = usuarioService.editarUsuario(id, usuarioDetails);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (MyException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (MyException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
