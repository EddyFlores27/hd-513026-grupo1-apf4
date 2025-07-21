package com.javaapp.veterinaria.Controller;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Services.LoginServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginServicio usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login"; //Redireccion al login
    }

    //Procesando login
   @PostMapping("/login")
    public String login(@RequestParam("email") String email, 
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return "redirect:/login?error=Debe completar todos los campos";
        }

        Usuario usuario = usuarioService.findByCorreo(email);

        if (usuario != null) {
            //Si la contraseña coincide
            if (passwordEncoder.matches(password, usuario.getContra())) {
                //Guardar los datos del usuario en la sesion
                session.setAttribute("usuario", usuario);
                return "redirect:/inicio"; // Redirige al inicio
            } else {
                //Contraseña incorrecta
                return "redirect:/login?contra";
            }
        }
        //Correo no encontrado
        return "redirect:/login?error";
    }    
}
