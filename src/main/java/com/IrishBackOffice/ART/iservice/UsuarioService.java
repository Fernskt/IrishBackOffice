/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.dto.UsuarioDTO;
import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.exceptions.MyException;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pc
 */
public interface UsuarioService extends UserDetailsService {
    @Transactional
    public Usuario save(UsuarioRegistroDTO registroDTO) throws MyException;
    
    @Transactional
    public Usuario editarUsuario(UUID id, UsuarioDTO usuarioDetails) throws MyException;
    
   public List<UsuarioDTO> listarUsuarios();
   
   public UsuarioDTO getUser(String email);
   
   public void eliminarUsuario(UUID id) throws MyException;
    
}
