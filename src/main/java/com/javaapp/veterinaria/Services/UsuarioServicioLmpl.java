package com.javaapp.veterinaria.Services;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.javaapp.veterinaria.DTO.UsuarioRegistroDTO;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Repository.UsuarioRepository;

@Service
public class UsuarioServicioLmpl implements UsuarioServicio {

    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicioLmpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void eliminarUsuario(int id) {
        // Verificar si el usuario existe antes de intentar eliminarlo
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }

        // Si el usuario existe, procedemos a eliminarlo
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario save(UsuarioRegistroDTO registroDTO) {
        // Validar DNI
        if (!registroDTO.getDni().matches("\\d{8}")) {
            throw new IllegalArgumentException("Ingrese los 8 digitos de su DNI");
        }

        // Validar nombres
        if (!registroDTO.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios.");
        }

        // Validar apellidos
        if (!registroDTO.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException("El apellido solo debe contener letras y espacios.");
        }

        // Validar correo (dominios Gmail y Hotmail)
        if (!registroDTO.getCorreo().matches("^[a-zA-Z0-9._%+-ñÑ]+@(gmail\\.com|hotmail\\.com)$")) {
            throw new IllegalArgumentException("El correo debe pertenecer al dominio Gmail o Hotmail.");
        }

        // Validar teléfono (solo números nacionales)
        if (!registroDTO.getTelefono().matches("^9\\d{8}$")) {
            throw new IllegalArgumentException("Solo se aceptan números nacionales");
        }

        // Validar contraseñas
        if (!registroDTO.getContra().equals(registroDTO.getRepassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        // Crear y guardar usuario si todo está correcto
        String encryptedPassword = passwordEncoder.encode(registroDTO.getContra()); // Creando una contraseña encriptada
        Usuario usuario = new Usuario(
            registroDTO.getDni(),
            registroDTO.getNombre(),
            registroDTO.getApellido(),
            registroDTO.getCorreo(),
            registroDTO.getTelefono(),
            encryptedPassword // Guardando la contraseña encriptada
        );

        // Guardar el usuario en el repositorio (base de datos)
        return usuarioRepository.save(usuario);
    }

    //Método para actualizar el usuario
    @Override
    public void actualizarUsuario(Usuario usuario) {
            if (usuarioRepository.existsById(usuario.getId())) {
                usuarioRepository.save(usuario);
            } else {
                throw new IllegalArgumentException("El usuario con ID " + usuario.getId() + " no existe.");
            }
        
    }

    // Métodos de verificación para correo, teléfono y DNI
    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existeTelefono(String telefono) {
        return usuarioRepository.existsByTelefono(telefono);
    }

    @Override
    public boolean existeDni(String dni) {
        return usuarioRepository.existsByDni(dni);
    }
}