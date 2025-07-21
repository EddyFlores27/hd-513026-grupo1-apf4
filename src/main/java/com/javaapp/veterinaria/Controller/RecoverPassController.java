package com.javaapp.veterinaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Repository.UsuarioRepository;

@Controller
public class RecoverPassController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    @GetMapping("/recoverpass")
    public String recoverpass() {
        return "RecoverPass"; 
    }

    @PostMapping("/restablecerContraseña")
    public String restablecerContraseña(@RequestParam("dni") String dni,
                                        @RequestParam("nuevaContraseña") String nuevaContraseña,
                                        Model model) {
        Usuario usuario = usuarioRepository.findByDni(dni); 
        
        if (usuario != null) {
            // Encriptar la nueva contraseña 
            String encryptedPassword = passwordEncoder.encode(nuevaContraseña);
            usuario.setContra(encryptedPassword);  
            usuarioRepository.save(usuario);  

            model.addAttribute("mensaje", "Contraseña restablecida con éxito.");
            return "RecoverPass"; 
        } else {
            model.addAttribute("error", "No se pudo encontrar el usuario con ese DNI.");
            return "RecoverPass"; 
        }
    }
}
