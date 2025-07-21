package com.javaapp.veterinaria.Services;
import com.javaapp.veterinaria.Model.Servicio;
import com.javaapp.veterinaria.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioServicio {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerTodosLosServicios() {
        return servicioRepository.findAll(); // Recuperar todos los servicios
    }
}
