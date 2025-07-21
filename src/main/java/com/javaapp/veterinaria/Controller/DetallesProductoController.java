package com.javaapp.veterinaria.Controller;

import com.javaapp.veterinaria.Model.Producto;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Services.ProductoServicio;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetallesProductoController {

    @Autowired
    private ProductoServicio productoService;

    @GetMapping("/detallesprod")
    public String mostrarDetallesProducto(@RequestParam("id") int id_producto, Model model, HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }

        // Obtener detalles del producto por ID
        Producto producto = productoService.obtenerProductoPorId(id_producto);
        model.addAttribute("producto", producto);
        return "DetallesProd";
    }

    @PostMapping("/agregarcarrito")
    public String agregarAlCarrito(@RequestParam("id_producto") int id_producto, @RequestParam("cantidad") int cantidad, HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // Lógica para agregar el producto al carrito (puedes almacenarlo en la sesión)
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new HashMap<>();
            }
            carrito.put(id_producto, carrito.getOrDefault(id_producto, 0) + cantidad);
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/tienda";
    }
}
