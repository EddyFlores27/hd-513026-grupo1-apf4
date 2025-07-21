package com.javaapp.veterinaria.Controller;

import com.javaapp.veterinaria.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String inicio(HttpSession session, Model model) {
        //Verifica si hay un usuario con sesion activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            //Si es asi los guarda en un modelo
            model.addAttribute("usuario", usuario);
        }
        return "Inicio";
    }
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
        return "Inicio";
    }
}
