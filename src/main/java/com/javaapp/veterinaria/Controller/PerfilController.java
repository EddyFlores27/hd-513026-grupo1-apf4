package com.javaapp.veterinaria.Controller;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Services.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfilController {

    private final UsuarioServicio usuarioServicio;

    // Inyección de dependencia a través del constructor
    public PerfilController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // Si es así, lo guarda en un modelo
            model.addAttribute("usuario", usuario);
        }
        return "Perfil";
    }

    @PostMapping("/perfil/actualizar")
    public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // Verificar si el correo, teléfono o DNI ya están registrados, solo si el valor es diferente al de la sesión
            if (!usuario.getCorreo().equals(usuarioActualizado.getCorreo()) && usuarioServicio.existeCorreo(usuarioActualizado.getCorreo())) {
                model.addAttribute("error", "El correo electrónico ya está registrado.");
                return "Perfil";
            }
            if (!usuario.getTelefono().equals(usuarioActualizado.getTelefono()) && usuarioServicio.existeTelefono(usuarioActualizado.getTelefono())) {
                model.addAttribute("error", "El teléfono ya está registrado.");
                return "Perfil";
            }
            if (!usuario.getDni().equals(usuarioActualizado.getDni()) && usuarioServicio.existeDni(usuarioActualizado.getDni())) {
                model.addAttribute("error", "El DNI ya está registrado.");
                return "Perfil";
            }
    
            // Si no hay errores, actualizamos los campos
            boolean actualizado = false;
            if (!usuario.getNombre().equals(usuarioActualizado.getNombre())) {
                usuario.setNombre(usuarioActualizado.getNombre());
                actualizado = true;
            }
            if (!usuario.getApellido().equals(usuarioActualizado.getApellido())) {
                usuario.setApellido(usuarioActualizado.getApellido());
                actualizado = true;
            }
            if (!usuario.getCorreo().equals(usuarioActualizado.getCorreo())) {
                usuario.setCorreo(usuarioActualizado.getCorreo());
                actualizado = true;
            }
            if (!usuario.getTelefono().equals(usuarioActualizado.getTelefono())) {
                usuario.setTelefono(usuarioActualizado.getTelefono());
                actualizado = true;
            }
            if (!usuario.getDni().equals(usuarioActualizado.getDni())) {
                usuario.setDni(usuarioActualizado.getDni());
                actualizado = true;
            }
    
            // Llamar al servicio para actualizar los datos en la base de datos solo si hay cambios
            if (actualizado) {
                usuarioServicio.actualizarUsuario(usuario);
                // Actualizar la sesión con el usuario actualizado
                session.setAttribute("usuario", usuario);
            }
        }
        return "redirect:/perfil";
    }
    
}
