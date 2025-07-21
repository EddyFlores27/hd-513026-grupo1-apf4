package com.javaapp.veterinaria.Services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String HOST_NAME = "smtp.gmail.com"; 
    private static final int SMTP_PORT = 465; 
    private static final String USERNAME = "trabajosprueba8@gmail.com"; 
    private static final String PASSWORD = "tmoc paik ewqy fgrk";
    private static final String FROM_EMAIL = "trabajosprueba8@gmail.com"; 

    public void sendEmail(String clienteEmail, String empresaEmail, String subject, String message) throws EmailException {
        // Configuración del cliente de correo
        SimpleEmail email = new SimpleEmail();
        email.setHostName(HOST_NAME);
        email.setSmtpPort(SMTP_PORT);
        email.setAuthentication(USERNAME, PASSWORD);
        email.setSSLOnConnect(true);

        // Dirección de origen
        email.setFrom(FROM_EMAIL);

        // Agregar destinatarios
        email.addTo(clienteEmail); // Enviar al cliente
        email.addCc(empresaEmail); // Copia a la empresa 

        // Asunto y contenido
        email.setSubject(subject);
        email.setMsg(message);

        // Enviar correo
        email.send();
    }
}