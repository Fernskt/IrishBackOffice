/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.dto.UsuarioDTO;
import com.IrishBackOffice.ART.dto.UsuarioRegistroDTO;
import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.exceptions.MyException;

import com.IrishBackOffice.ART.iservice.UsuarioService;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
    public Usuario editarUsuario(UUID id, UsuarioDTO usuarioDetails) throws MyException {
        
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new MyException("Usuario no encontrado con id: " + id));

        usuario.setRol(usuarioDetails.getRol());
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setDni(usuarioDetails.getDni());

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
        }
        return new User(
                usuario.getEmail().toLowerCase(),
                usuario.getContra(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        );
    }

    @Override
    public UsuarioDTO getUser(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setDni(usuario.getDni());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol());
        return usuarioDTO;
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
    public void eliminarUsuario(UUID id) throws MyException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new MyException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }

    public void validaciones(UsuarioRegistroDTO registroDTO) throws MyException {
        List<UsuarioDTO> usuarios = listarUsuarios();

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
