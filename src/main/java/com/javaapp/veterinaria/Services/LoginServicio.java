package com.javaapp.veterinaria.Services;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServicio {

    @Autowired
    private LoginRepository usuarioRepository;

    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}