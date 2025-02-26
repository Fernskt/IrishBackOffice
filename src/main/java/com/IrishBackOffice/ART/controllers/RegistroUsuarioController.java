/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.enums.Rol;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro")
public class RegistroUsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
    }
    
    @ModelAttribute("roles")
    public Rol[] getRoles() {
        return Rol.values();
    }
    
    @GetMapping
    public ResponseEntity<String> mostrarFormRegistro(){
        return new ResponseEntity<>("Registro de usuario", HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        try {
            usuarioService.save(usuarioRegistroDTO);
            return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.CREATED);
        } catch (MyException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
