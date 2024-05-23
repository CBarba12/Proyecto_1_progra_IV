package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<ProveedorEntity> login(@RequestBody ProveedorEntity proveedor) {
        if (proveedorService.verificarEmailPaswor(proveedor.getIdProveedor(), proveedor.getContrasena())) {
            ProveedorEntity p=proveedorService.obtenerProveedorPorId(proveedor.getIdProveedor());
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/inicio")
    public String inicio( ) {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }





}


