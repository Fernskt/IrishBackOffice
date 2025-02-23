/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.iservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String listarUsuarios(Model model){
        
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        
        return "listarUsuarios";
    }
    
    
}
