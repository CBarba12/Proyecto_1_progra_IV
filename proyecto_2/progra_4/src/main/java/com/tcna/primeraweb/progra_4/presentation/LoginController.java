package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
                        HttpSession session) {
        // Verificar las credenciales del proveedor

        if (proveedorService.verificarEmailPaswor(numeroIdentificacion, contrasena)) {

            // Si las credenciales son válidas, obtener el proveedor autenticado
            ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(numeroIdentificacion);

            if (proveedor != null) {

                // Obtener los clientes asociados al proveedor autenticado
                List<ClienteEntity> clientes = clienteService.obtenerClientesPorProveedor(numeroIdentificacion);

                // Guardar los clientes en la sesión para usarlos en la siguiente solicitud
                
                session.setAttribute("clientes", clientes);
                // Redirigir a la página de lista de clientes
                return "redirect:/ClienteController/Listadeclientes";
            }
        }
        // Si las credenciales son inválidas, redirigir de vuelta al formulario de inicio de sesión
        return "redirect:/LoginController/inicio";
    }



    @GetMapping("/inicio")
    public String inicio( ) {
          return "login";
        }




}


