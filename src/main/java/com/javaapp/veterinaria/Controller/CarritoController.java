package com.javaapp.veterinaria.Controller;
import com.javaapp.veterinaria.Model.ItemCarrito;
import com.javaapp.veterinaria.Model.Producto;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Services.ProductoServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoServicio productoService;

    @GetMapping
public String verCarrito(HttpSession session, Model model) {
    // Verifica si hay un usuario con sesión activa
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        model.addAttribute("usuario", usuario);
    }

    // Obtener el carrito de la sesión
    @SuppressWarnings("unchecked")
    Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

    List<ItemCarrito> items = new ArrayList<>();
    double total = 0;

    if (carrito != null) {
        items = carrito.entrySet().stream()
                .map(entry -> {
                    Producto producto = productoService.obtenerProductoPorId(entry.getKey());
                    int cantidad = entry.getValue();
                    return new ItemCarrito(producto, cantidad);
                })
                .collect(Collectors.toList());

        // Calcular el total
        total = items.stream()
                .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
                .sum();
    }

    // Formatear total y subtotal con dos decimales
    String totalFormatted = String.format("%.2f", total);
    model.addAttribute("items", items);
    model.addAttribute("total", totalFormatted);

    return "Carrito";
}


    @PostMapping("/actualizar")
    public String actualizarCantidad(@RequestParam("id_producto") int idProducto,
                                     @RequestParam("cantidad") int cantidad, HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

        if (carrito != null && carrito.containsKey(idProducto)) {
            if (cantidad > 0) {
                carrito.put(idProducto, cantidad);
            } else {
                carrito.remove(idProducto);
            }
        }

        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarDelCarrito(@RequestParam("id_producto") int idProducto, HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.remove(idProducto);
        }

        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        }

        session.setAttribute("carrito", null);
        return "redirect:/carrito";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam("id_producto") int idProducto,
                                  @RequestParam("cantidad") int cantidad,
                                  HttpSession session) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        }

        System.out.println("Producto ID: " + idProducto + ", Cantidad: " + cantidad);  // Depuración
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
        }
        carrito.put(idProducto, carrito.getOrDefault(idProducto, 0) + cantidad);
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }
}
