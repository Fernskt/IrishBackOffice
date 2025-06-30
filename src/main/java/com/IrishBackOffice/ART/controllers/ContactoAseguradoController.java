package com.IrishBackOffice.ART.controllers;

import com.IrishBackOffice.ART.dto.ContactoAseguradoDTO;
import com.IrishBackOffice.ART.exceptions.MyException;
import com.IrishBackOffice.ART.iservice.ContactoAseguradoService;
import com.IrishBackOffice.ART.mapper.ContactoAseguradoMapper;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/contactos")
public class ContactoAseguradoController {

    private final ContactoAseguradoService service;
    private final ContactoAseguradoMapper mapper;

    @Autowired
    public ContactoAseguradoController(ContactoAseguradoService service,
                                       ContactoAseguradoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ContactoAseguradoDTO>> listarContactos() {
        try {
            List<ContactoAseguradoDTO> dtos = service
                .listarContactos()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        } catch (MyException e) {
            // MyException extiende de Exception, así que lo atrapamos aquí
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
