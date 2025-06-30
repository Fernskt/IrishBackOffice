package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.ContactoAsegurado;
import com.IrishBackOffice.ART.exceptions.MyException;

import java.util.List;

public interface ContactoAseguradoService {
    List<ContactoAsegurado> listarContactos() throws MyException;
}
