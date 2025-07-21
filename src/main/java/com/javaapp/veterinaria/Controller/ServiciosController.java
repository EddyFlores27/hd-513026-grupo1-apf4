package com.javaapp.veterinaria.Controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.javaapp.veterinaria.Model.Citas;
import com.javaapp.veterinaria.Model.Servicio;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Repository.ServicioRepository;
import com.javaapp.veterinaria.Services.CitasServicio;
import com.javaapp.veterinaria.Services.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ServiciosController {

    @Autowired
    private CitasServicio citasServicio; // Inyección de CitasServicio

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/servicio")
    public String servicio(HttpSession session, Model model) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // Si es así, lo guarda en un modelo
            model.addAttribute("usuario", usuario);
        }

            // Cargar todos los servicios y enviarlos al modelo
    List<Servicio> servicios = servicioRepository.findAll();
    model.addAttribute("servicios", servicios);
        return "Servicio";
    }
    @PostMapping("/crear-cita")
    public String crearCita(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("fecha") String fecha,
            @RequestParam("hora") String hora,
            @RequestParam("servicio") Integer idServicio,
            Model model) {

        // Crear una instancia de Citas
        Citas nuevaCita = new Citas();
        nuevaCita.setNombreC(nombre);
        nuevaCita.setApellidoC(apellido);
        nuevaCita.setTelefonoC(telefono);
        nuevaCita.setCorreoC(correo);
        nuevaCita.setFechaC(LocalDate.parse(fecha));
        nuevaCita.setHoraC(LocalTime.parse(hora));
        nuevaCita.setIdServicio(idServicio);

        // Guardar la cita en la base de datos
        citasServicio.saveCita(nuevaCita);

        // Obtener el nombre del servicio con el idServicio
        Servicio servicio = servicioRepository.findById(idServicio).orElse(null);
        String nombreServicio = servicio != null ? servicio.getNombs() : "Servicio no encontrado";

        // Configurar correo
        String subject = "Confirmación de Reserva - " + nombre + " " + apellido;
        String message = "Hola " + nombre + ",\n\n" +
                "Tu cita ha sido registrada con éxito:\n" +
                "- Fecha: " + fecha + "\n" +
                "- Hora: " + hora + "\n" +
                "- Servicio: " + nombreServicio + "\n" +
                "- Teléfono: " + telefono + "\n\n" +
                "Gracias por confiar en nosotros.\n\n" +
                "Atentamente,\nEquipo de la Empresa.";

        try {
            // Usar la instancia inyectada del servicio para enviar el correo
            emailService.sendEmail(correo, "trabajosprueba8@gmail.com", subject, message);
        } catch (EmailException e) {
            e.printStackTrace();
            model.addAttribute("error", "No se pudo enviar el correo de confirmación.");
        }
        // Redirigir a la vista de servicios
        model.addAttribute("mensaje", "Cita creada exitosamente y correo enviado.");
        return "redirect:/servicio";
    }
}