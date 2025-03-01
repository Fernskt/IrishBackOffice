/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.repositories;

import com.IrishBackOffice.ART.entities.Usuario;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pc
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    public Usuario findByEmail(String email);
  
}
