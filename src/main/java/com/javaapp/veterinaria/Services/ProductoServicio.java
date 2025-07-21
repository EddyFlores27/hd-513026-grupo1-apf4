package com.javaapp.veterinaria.Services;
import com.javaapp.veterinaria.Model.Producto;
import com.javaapp.veterinaria.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    public Producto obtenerProductoPorId(int id_producto) {
        return productoRepository.findById(id_producto).orElse(null);
    }    
}
