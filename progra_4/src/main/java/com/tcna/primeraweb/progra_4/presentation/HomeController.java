package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/homecontroler")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Este método devuelve el nombre de la plantilla Thymeleaf a la que se debe redirigir
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Verificar si hay clientes asociados al proveedor en la sesión
        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }
        return "proveedorAcciones"; // Este método devuelve el nombre de la plantilla Thymeleaf a la que se debe redirigir
    }

}
