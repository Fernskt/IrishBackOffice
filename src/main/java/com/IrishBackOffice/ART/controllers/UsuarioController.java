/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.iservice.UsuarioService;
import com.IrishBackOffice.ART.dto.UsuarioDTO;
import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
/**
 *
 * @author Pc
 */
@RestController
@RequestMapping("/api/usuarios") // Cambié la ruta para seguir convenciones REST
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios); // Devuelve un JSON con la lista de usuarios y código HTTP 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado con éxito.");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
    }
    @PutMapping("/{id}")
public ResponseEntity<String> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRegistroDTO registroDTO) {
    // Llamar al servicio para editar el usuario
    usuarioService.editarUsuario(id, registroDTO);
    return ResponseEntity.ok("Usuario actualizado con éxito.");
}

}
