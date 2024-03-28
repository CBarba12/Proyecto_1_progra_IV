package com.tcna.primeraweb.project_progra.presentation;


import com.tcna.primeraweb.project_progra.logic.ProveedorEntity;
import com.tcna.primeraweb.project_progra.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ personas")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public  String listarProveedor(Model model){
        List<ProveedorEntity> personas=proveedorService.obtenerProveedores();
        model.addAttribute("listarProveedores",personas);
        return "listar";
    }

    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute ProveedorEntity persona){
        proveedorService.crearPersona(persona);
        return "redirect:/personas";
    }


}
