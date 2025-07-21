package com.javaapp.veterinaria.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaapp.veterinaria.Model.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
    @GetMapping("/blog")
    public String blog(HttpSession session, Model model) {
        //Verifica si hay un usuario con sesion activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            //Si es asi los guarda en un modelo
            model.addAttribute("usuario", usuario);
        }
        return "BlogP";
    }
}
