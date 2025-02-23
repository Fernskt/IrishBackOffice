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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pc
 */
@Controller
@RequestMapping("/registro")
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
    public String mostrarFormRegistro(){
        return "registro";
    }
    
    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioRegistroDTO, ModelMap model) {
        
        try {
            usuarioService.save(usuarioRegistroDTO);
        } catch (MyException ex) {
            model.put("error", ex.getMessage());
            return "registro";
        }
        return "redirect:/usuario/listar?exito";
    }
    
}
