package com.tcna.primeraweb.progra_4.presentation.proveedor;

import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Proveedor_controller")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("/listar") // Añade esta línea para mapear el método a la URL

    public String listarPeronas(Model model){

        List<ProveedorEntity> provedores=proveedorService.ObtenerProveedores();

        model.addAttribute("listaProveedor", provedores);

        return "listar";
    }


    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute ProveedorEntity persona){
        proveedorService.crearProveedores(persona);
        return "redirect:/personas";
    }


}
