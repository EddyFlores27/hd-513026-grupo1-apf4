package com.javaapp.veterinaria.Controller;
import com.javaapp.veterinaria.Model.Usuario;
import com.javaapp.veterinaria.Services.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class TiendaController {

    @Autowired
    private ProductoServicio productoService;

    @GetMapping("/tienda")
    public String mostrarTienda(HttpSession session, Model model) {
        // Verifica si hay un usuario con sesión activa
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // Si hay un usuario en sesión, lo agrega al modelo
            model.addAttribute("usuario", usuario);
        }

        // Agrega los productos divididos por categoría al modelo
        model.addAttribute("alimentos", productoService.obtenerProductosPorCategoria("Alimentos"));
        model.addAttribute("accesorios", productoService.obtenerProductosPorCategoria("Accesorios"));
        model.addAttribute("medicinas", productoService.obtenerProductosPorCategoria("Medicinas"));
        return "Tienda"; // Devuelve la vista de la tienda
    }
}
