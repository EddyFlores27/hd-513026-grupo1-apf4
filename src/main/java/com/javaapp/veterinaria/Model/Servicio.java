package com.javaapp.veterinaria.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios") 
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ids;
    private String nombs;
    private Double precios;

    // Getters y Setters
    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public String getNombs() {
        return nombs;
    }

    public void setNombs(String nombs) {
        this.nombs = nombs;
    }

    public Double getPrecios() {
        return precios;
    }

    public void setPrecios(Double precios) {
        this.precios = precios;
    }
}
