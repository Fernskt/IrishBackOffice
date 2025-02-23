/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;import org.springframework.ui.Model;
;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Pc
 */
@Controller
public class PortalController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }
    
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
    }
    
@GetMapping("/")
    public String home(Model model){
        
         // o usuario.getNombre()
        return "home";
    }
}
