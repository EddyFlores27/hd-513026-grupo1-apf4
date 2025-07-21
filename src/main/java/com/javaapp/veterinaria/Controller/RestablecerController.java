package com.javaapp.veterinaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Repository.UsuarioRepository;
import com.javaapp.veterinaria.Services.RecuperarEmailService;


@Controller
public class RestablecerController {

    @Autowired
    private RecuperarEmailService recuperarEmailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/restablecer")
    public String restablecer() {
        return "Restablecer";  
    }

    @PostMapping("/enviarCorreoRecuperacion")
    public String enviarCorreoRecuperacion(@RequestParam("email") String email, @RequestParam("dni") String dni, Model model) {
        // Verificar que el correo y el DNI existan en la base de datos
        Usuario usuario = usuarioRepository.findByCorreoAndDni(email, dni);
        if (usuario != null) {
            // Enlace
            String enlace = "http://localhost:8080/recoverpass"; 
            try {
                recuperarEmailService.enviarCorreoRecuperacion(email, enlace);
                model.addAttribute("mensaje", "Correo enviado con éxito. Revisa tu bandeja de entrada.");
            } catch (Exception e) {
                model.addAttribute("error", "Hubo un error al enviar el correo: " + e.getMessage());
            }
        } else {
            model.addAttribute("error", "El correo o DNI no son válidos.");
        }
        return "Restablecer"; 
    }
}

