package com.javaapp.veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapp.veterinaria.Model.Citas;

public interface CitasRepository extends JpaRepository <Citas, Integer>{
    
}
