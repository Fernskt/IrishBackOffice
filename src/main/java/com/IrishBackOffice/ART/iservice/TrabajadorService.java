package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.Trabajador;
import com.IrishBackOffice.ART.exceptions.MyException;

import java.util.List;
import java.util.UUID;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador) throws MyException;
    List<Trabajador> listarTrabajadores();
    Trabajador findById(UUID id) throws MyException;
    Trabajador editarTrabajador(Trabajador trabajador) throws MyException;
    void eliminarTrabajador(UUID id) throws MyException;
}
