package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.ProveedorDTO;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import com.tcna.primeraweb.progra_4.service.UsuarioServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home")
public class UsuarioController {


    @Autowired
    private UsuarioServices usuarioServices =new UsuarioServices();




    @GetMapping("/login/{id}/{password}")
    public Boolean login(@PathVariable("id") String email, @PathVariable("password") String password) {
        if (usuarioServices.login(email, password)) {
            return true;
        } else {
            return false;
        }
    }





}
