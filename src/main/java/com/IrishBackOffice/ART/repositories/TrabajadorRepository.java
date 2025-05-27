/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.repositories;

import com.IrishBackOffice.ART.entities.Trabajador;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pc
 */
public interface TrabajadorRepository extends JpaRepository<Trabajador, UUID>{
    
}
