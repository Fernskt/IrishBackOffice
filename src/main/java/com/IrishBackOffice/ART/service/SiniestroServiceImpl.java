/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.SiniestroService;
import com.IrishBackOffice.ART.repositories.SiniestroRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class SiniestroServiceImpl implements SiniestroService{
    
    @Autowired
    SiniestroRepository siniestroRepository;
    
    @Override
    public Siniestro save(Siniestro siniestro) throws MyException{
        
        validaciones(siniestro);
       
        LocalDateTime fechaIngreso = LocalDateTime.now();
        siniestro.setFechaIngreso(fechaIngreso);
        siniestro.setFecha_vencimiento(fechaIngreso.plusDays(10));
      
        siniestroRepository.save(siniestro);
        return siniestro;
    }

    @Override
    public List <Siniestro> listarSiniestros() {
        List <Siniestro> siniestros = siniestroRepository.findAll();
        return siniestros;
    }
    
    public void validaciones(Siniestro siniestro) throws MyException{
         if(siniestro.getNumStro() == 0){
            throw new MyException("El num de siniestro no puede ser 0 o estar vacío");
        }
        if(siniestro.getLugar_direccion().isEmpty()){
            throw new MyException("Lugar del hecho no puede ser vacío");
        }
        if(siniestro.getLugar_entrecalles().isEmpty()){
            throw new MyException("Complete las entre calles");
        }
        if(siniestro.getLocalidad().isEmpty()){
            throw new MyException("Localidad no puede estar vacío");
        }
        if(siniestro.getMechanicaHecho().isEmpty()){
            throw new MyException("Mecánica del hecho, no puede estar vacío");
        }
        if(siniestro.getGravedad().equals("0") || siniestro.getGravedad().isEmpty()){
            throw new MyException("Debe seleccionar la gravedad");
        }
        if(siniestro.getTipoInvestigacion().equalsIgnoreCase("0") || siniestro.getTipoInvestigacion().isEmpty()){
            throw new MyException("Debe seleccionar tipo de investigación");
        }
        if(siniestro.getLesiones().isEmpty() || siniestro.getLesiones() == null){
            throw new MyException("Debe completar Lesiones");
        }
        if(siniestro.getPatologiasInculpables().isEmpty() || siniestro.getPatologiasInculpables() == null){
            throw new MyException("Debe ingresar Patologías inculpables");
        }
        if(siniestro.getTipoStro().isEmpty() || siniestro.getTipoStro() == null){
            throw new MyException("Debe ingresar el tipo de Siniestro");
        }
    }
    
}
