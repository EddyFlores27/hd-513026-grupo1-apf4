package com.javaapp.veterinaria.Services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RecuperarEmailService {
    @Value("${spring.mail.username}")
    private String fromEmail; 

    @Value("${spring.mail.password}")
    private String password;  

    
    public void enviarCorreoRecuperacion(String destinatario, String enlace) throws EmailException {
        
        HtmlEmail email = new HtmlEmail();

        // Configuración del correo
        email.setHostName("smtp.gmail.com"); 
        email.setSmtpPort(587); 
        email.setAuthentication(fromEmail, password); 
        email.setStartTLSEnabled(true); 

        // Dirección de empresa
        email.setFrom(fromEmail, "PetCare");

        // Agregar destinatario
        email.addTo(destinatario); 

        // Asunto del correo
        email.setSubject("Recuperación de contraseña");

        // Contenido del correo (el enlace de recuperación)
        email.setMsg("Haga clic en el siguiente enlace para restablecer su contraseña: " + enlace);
        email.send();
    }
}
