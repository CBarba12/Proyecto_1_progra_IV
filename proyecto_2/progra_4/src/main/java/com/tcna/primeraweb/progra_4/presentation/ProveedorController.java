package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
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
@RequestMapping("/ProveedorController")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("/Listadeproveedores") // Añade esta línea para mapear el método a la URL
    public String listaProveedor(Model model){
        List<ProveedorEntity> proveedores=  proveedorService.ObtenerProveedores();
   
        model.addAttribute("listaProveedor", proveedores);
        return "listar";
    }

    @GetMapping("/nuevo")
   public String MostrarFormularioNuevoProveedor(Model model){
        model.addAttribute("proveedor",new ProveedorEntity());
        model.addAttribute("accion","/ProveedorController/nuevo");
        return "formulario";
   }


    @PostMapping("/nuevo")
   public String guardarNuevoProveedor(@ModelAttribute ProveedorEntity proveedor){

        proveedorService.crearProveedores(proveedor);

        return "redirect:/ProveedorController/Listadeproveedores";
   }














    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute ProveedorEntity persona){
        proveedorService.crearProveedores(persona);
        return "redirect:/personas";
    }


}
