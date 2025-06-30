package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.ContactoAsegurado;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.ContactoAseguradoService;
import com.IrishBackOffice.ART.repositories.ContactoAseguradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoAseguradoServiceImpl implements ContactoAseguradoService {

    @Autowired
    private ContactoAseguradoRepository repo;

    @Override
    public List<ContactoAsegurado> listarContactos() throws MyException {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new MyException("Error al listar contactos: " + e.getMessage());
        }
    }
}
