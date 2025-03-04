package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.dto.SiniestroDTO;
import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import java.util.List;

public interface SiniestroService {
    
    // Guarda un nuevo siniestro
    Siniestro save(Siniestro siniestro) throws MyException;
    
    // Lista todos los siniestros
    List<Siniestro> listarSiniestros();
    
    // Busca un siniestro por ID
    Siniestro findById(Long id) throws MyException;
    
    // Edita un siniestro
    Siniestro editarSiniestro(SiniestroDTO siniestroDTO, Long id) throws MyException;
    
    // Elimina un siniestro
    void delete(Siniestro siniestro) throws MyException;
}
