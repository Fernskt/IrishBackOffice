package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.Art;
import com.IrishBackOffice.ART.exceptions.MyException;

import java.util.List;

public interface ArtService {
    Art save(Art art) throws MyException;
    List<Art> listarARTs();
    Art findById(Long id) throws MyException;
    Art editarArt(Art art) throws MyException;
    void eliminarArt(Long id) throws MyException;
}
