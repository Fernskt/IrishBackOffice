/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Pc
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

     @ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = null;
            
            if (principal instanceof User) {
                User user = (User) principal;
                username = user.getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            }
            
            if (username != null) {
                Usuario usuario = usuarioRepository.findByEmail(username);
                if (usuario != null) {
                    model.addAttribute("userName", usuario.getNombre() + " " + usuario.getApellido());
                } else {
                    // Manejar el caso donde no se encuentra el usuario en la base de datos
                    model.addAttribute("userName", "Invitado");
                }
            }
        }
    }
}
