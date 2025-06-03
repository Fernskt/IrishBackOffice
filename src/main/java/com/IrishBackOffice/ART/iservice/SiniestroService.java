package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.dto.SiniestroDTO;
import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.exceptions.MyException;
import java.util.List;
import java.util.UUID;

public interface SiniestroService {
    
    // Guarda un nuevo siniestro
    Siniestro save(SiniestroDTO siniestroDTO) throws MyException;
    
    // Lista todos los siniestros
    List<Siniestro> listarSiniestros();
    
    // Busca un siniestro por ID
    Siniestro findById(Long id) throws MyException;
    
    // Edita un siniestro
    Siniestro editarSiniestro(SiniestroDTO siniestroDTO, Long id) throws MyException;
    
    // Elimina un siniestro
    void delete(Siniestro siniestro) throws MyException;
    
     public void asignarAnalista(Long id, UUID analistaId) throws MyException;
     
     List<Siniestro> listarPorFiltrosOpcionales(
        String tipoStro, 
        String tipoInvestigacion, 
        String resultado,
         Long artId
    );
}
