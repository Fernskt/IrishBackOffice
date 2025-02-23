package com.IrishBackOffice.ART.service;

import com.IrishBackOffice.ART.entities.Usuario;
import com.IrishBackOffice.ART.enums.Rol;
import com.IrishBackOffice.ART.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class AdminService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void cargarAdmin(Rol rol, String email, String contra, Long dni, String nombre, String apellido) {
        // Llamada al método de validaciones
        validaciones(rol, email, contra, dni, nombre, apellido);

        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContra(passwordEncoder.encode(contra)); // Hashing de la contraseña

        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario: " + e.getMessage(), e);
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método de validaciones
    public void validaciones(Rol rol, String email, String contra, Long dni, String nombre, String apellido) throws IllegalArgumentException {
        if (rol == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (!StringUtils.hasText(contra)) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (dni == null) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }
        if (!StringUtils.hasText(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (!StringUtils.hasText(apellido)) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }

        // Verificar formato del email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Formato de email no válido");
        }
    }
}
