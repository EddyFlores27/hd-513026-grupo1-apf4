package com.javaapp.veterinaria.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaapp.veterinaria.Model.Citas;
import com.javaapp.veterinaria.Repository.CitasRepository;

@Service
public class CitasServicio {

    @Autowired
    private CitasRepository citasRepository;

    // Método para guardar una cita
    public Citas saveCita(Citas cita) {
        return citasRepository.save(cita);
    }
}
