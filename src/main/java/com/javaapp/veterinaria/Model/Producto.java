package com.javaapp.veterinaria.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    private int id_producto;
    private String nom_prod;
    private String marca;
    private double precio;
    private String disponibilidad;
    private String categoria;

    public int getId_producto() {
        return id_producto;
    }
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public String getNom_prod() {
        return nom_prod;
    }
    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
