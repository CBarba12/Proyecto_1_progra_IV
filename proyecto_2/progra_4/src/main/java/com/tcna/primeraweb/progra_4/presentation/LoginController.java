package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public String login(@RequestParam("numero_identificacion") String numeroIdentificacion,
                        @RequestParam("contrasena") String contrasena,
                        HttpSession session, Model model) {


        if (proveedorService.verificarEmailPaswor(numeroIdentificacion, contrasena)) {

            ProveedorEntity p=proveedorService.obtenerProveedorPorId(numeroIdentificacion);

            if (p.getAdmin() != null && p.getAdmin() == 1) {

                List<ProveedorEntity> prob=proveedorService.ObtenerProveedores();

                model.addAttribute("listaProveedor",prob);
                return "listarproveedor";

            } else {
                session.setAttribute("id_proveedor",numeroIdentificacion);
                return "proveedorAcciones";
            }

        }


        return "redirect:/LoginController/inicio";
    }



    @GetMapping("/inicio")
    public String inicio( ) {
          return "login";
        }




}


