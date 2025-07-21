package com.javaapp.veterinaria.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //Eliminar la sesion del usuario
        session.invalidate();
        return "redirect:/inicio";
    }
}
