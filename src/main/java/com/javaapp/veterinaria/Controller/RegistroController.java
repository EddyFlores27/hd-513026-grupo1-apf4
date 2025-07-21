package com.javaapp.veterinaria.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaapp.veterinaria.DTO.UsuarioRegistroDTO;
import com.javaapp.veterinaria.Services.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroController {
    @GetMapping("/registro")
    public String registro() {
        return "Registro";
    }
    
    private UsuarioServicio usuarioServicio;

    public RegistroController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, RedirectAttributes redirectAttributes) {
        // Verificar si el correo ya está registrado
        if (usuarioServicio.existeCorreo(registroDTO.getCorreo())) {
            redirectAttributes.addFlashAttribute("error", "Este correo ya está registrado.");
            redirectAttributes.addFlashAttribute("usuario", registroDTO);
            return "redirect:/registro";
        }

        // Verificar si el teléfono ya está registrado
        if (usuarioServicio.existeTelefono(registroDTO.getTelefono())) {
            redirectAttributes.addFlashAttribute("error", "Ya hay un teléfono registrado con este número.");
            redirectAttributes.addFlashAttribute("usuario", registroDTO);
            return "redirect:/registro";
        }

        // Verificar si el DNI ya está registrado
        if (usuarioServicio.existeDni(registroDTO.getDni())) {
            redirectAttributes.addFlashAttribute("error", "Verifique sus datos. El DNI ya está registrado.");
            redirectAttributes.addFlashAttribute("usuario", registroDTO);
            return "redirect:/registro";
        }

        // Verificar si las contraseñas coinciden
        if (!registroDTO.getContra().equals(registroDTO.getRepassword())) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden.");
            redirectAttributes.addFlashAttribute("usuario", registroDTO);
            return "redirect:/registro";
        }

        // Si todas las validaciones pasan, guardar el usuario
        usuarioServicio.save(registroDTO);
        return "redirect:/login";
    }
}
