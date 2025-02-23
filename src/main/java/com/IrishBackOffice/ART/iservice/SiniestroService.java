/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import java.util.List;

/**
 *
 * @author Pc
 */
public interface SiniestroService {
    
    public Siniestro save(Siniestro siniestro) throws MyException;
    public List <Siniestro> listarSiniestros();
}
