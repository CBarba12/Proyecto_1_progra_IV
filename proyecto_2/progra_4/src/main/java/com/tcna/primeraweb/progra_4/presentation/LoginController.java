package com.tcna.primeraweb.progra_4.presentation;


import org.springframework.ui.Model;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private ProveedorService proveedorService;



        @PostMapping("/login")
        public String login(@RequestParam("numero_identificacion") String numeroIdentificacion,
                            @RequestParam("contrasena") String contrasena,
                            Model model) {




            if (proveedorService.verificarEmailPaswor( numeroIdentificacion,  contrasena)) {
                // Credenciales válidas, redireccionar a una página de inicio de sesión exitosa


                return "redirect:/ClienteController/Listadeclientes";
            } else {
                // Credenciales inválidas, mostrar un mensaje de error y volver al formulario de inicio de sesión
                return "redirect:/LoginController/inicio";
            }
        }

    @GetMapping("/inicio")
    public String inicio( ) {
          return "login";
        }




}


