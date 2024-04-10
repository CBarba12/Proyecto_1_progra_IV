package com.tcna.primeraweb.progra_4.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/homecontroler")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Este método devuelve el nombre de la plantilla Thymeleaf a la que se debe redirigir
    }
}
