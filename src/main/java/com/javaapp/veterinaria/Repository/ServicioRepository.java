package com.javaapp.veterinaria.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javaapp.veterinaria.Model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
