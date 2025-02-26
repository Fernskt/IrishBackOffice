/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.dto.UsuarioDTO;
import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.enums.Rol;
import com.IrishBackOffice.ART.exceptions.MyException;
import org.springframework.transaction.annotation.Transactional;
import com.IrishBackOffice.ART.iservice.UsuarioService;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public Usuario save(UsuarioRegistroDTO registroDTO) throws MyException {
        validaciones(registroDTO);
        
        Usuario usuario = new Usuario(
            registroDTO.getRol(), 
            registroDTO.getEmail().toLowerCase(), 
            passwordEncoder.encode(registroDTO.getContra()), 
            registroDTO.getSiniestros(), 
            registroDTO.getDni(), 
            registroDTO.getNombre(), 
            registroDTO.getApellido()
        );
        
        return usuarioRepository.save(usuario);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
        }
        return new User(usuario.getEmail().toLowerCase(), usuario.getContra(), mapearRol(usuario.getRol()));
    }
    
    private Collection<? extends GrantedAuthority> mapearRol(Rol rol) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }
    
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                    usuario.getDni(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getRol(),
                    usuario.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
        @Override
    public Usuario editarUsuario(Long id, UsuarioRegistroDTO registroDTO) {
        try {
            // Buscar el usuario por ID
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new MyException("Usuario no encontrado"));
            
            // Actualizar los campos con los nuevos datos del DTO
            usuario.setDni(registroDTO.getDni());
            usuario.setNombre(registroDTO.getNombre());
            usuario.setApellido(registroDTO.getApellido());
            usuario.setRol(registroDTO.getRol());
            usuario.setEmail(registroDTO.getEmail().toLowerCase());
            usuario.setContra(passwordEncoder.encode(registroDTO.getContra()));  // Si es necesario actualizar la contraseña
            
            // Guardar el usuario actualizado
            return usuarioRepository.save(usuario);
        } catch (MyException ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public void validaciones(UsuarioRegistroDTO registroDTO) throws MyException {
        List<UsuarioDTO> usuarios = listarUsuarios();
        
        if (registroDTO.getDni() == null) {
            throw new MyException("El DNI no puede ser nulo");
        }
        if (registroDTO.getNombre() == null || registroDTO.getNombre().isEmpty()) {
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
        if (registroDTO.getApellido() == null || registroDTO.getApellido().isEmpty()) {
            throw new MyException("El apellido no puede ser nulo o estar vacío");
        }
        if (registroDTO.getEmail() == null || registroDTO.getEmail().isEmpty()) {
            throw new MyException("El Email no puede ser nulo o estar vacío");
        }
        if (registroDTO.getContra() == null || registroDTO.getContra().isEmpty()) {
            throw new MyException("Ingrese la contraseña");
        }
        if (registroDTO.getContra().length() < 8) {
            throw new MyException("La contraseña debe tener más de 8 caracteres");
        }
        
        for (UsuarioDTO usuario : usuarios) {
            if (Objects.equals(registroDTO.getDni(), usuario.getDni())) {
                throw new MyException("Ya estás registrado con ese DNI");
            }
            if (registroDTO.getEmail().equals(usuario.getEmail())) {
                throw new MyException("Nombre de Usuario ya existe");
            }
        }
    }
}
