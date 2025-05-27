/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.Asegurado;
import com.IrishBackOffice.ART.exceptions.MyException;
import java.util.List;

/**
 *
 * @author Pc
 */
public interface IAseguradoService {
    
    Asegurado save(Asegurado asegurado) throws MyException;
    
    List <Asegurado> listarAsegurados();
    
    Asegurado findById(Long id) throws MyException;
    
    Asegurado editarAsegurado(Asegurado asegurado) throws MyException;
    
    void eliminarAsegurado(Long id) throws MyException;
    
}
