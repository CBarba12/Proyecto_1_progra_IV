package com.tcna.primeraweb.progra_4.presentation;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/homecontroler")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Este método devuelve el nombre de la plantilla Thymeleaf a la que se debe redirigir
    }
    @GetMapping("/ProveedorAcciones")
    public String ProveedorAcciones(Model model, HttpSession session) {
        return "proveedorAcciones"; // Este método devuelve el nombre de la plantilla Thymeleaf a la que se debe redirigir
    }
}
