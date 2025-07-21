package com.javaapp.veterinaria.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaapp.veterinaria.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {    
    boolean existsByCorreo(String correo);
    boolean existsByTelefono(String telefono);
    boolean existsByDni(String dni);

    Usuario findByCorreoAndDni(String correo, String dni);

    Usuario findByDni(String dni);
    
} 