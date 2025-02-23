/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/siniestros")
public class SiniestroController {
    
    @Autowired
    SiniestroService siniestroService;
    
    @GetMapping
    public String siniestros(Model model) {
        model.addAttribute("siniestro", new Siniestro());
        return "formSiniestro";
    }
    
    @GetMapping("/listar")
    public String listarSiniestros(Model model){
      model.addAttribute("siniestros", siniestroService.listarSiniestros())  ;
        return "listarSiniestros";
    }
    
    @PostMapping("/cargar")
    public String cargarSiniestro(@ModelAttribute("siniestro") Siniestro siniestro, ModelMap model){
        try {
            siniestroService.save(siniestro);
        } catch (MyException ex) {
            model.put("error", ex.getMessage());
            return "formSiniestro";
        }
          return "redirect:/siniestros?exito";
    }
}
