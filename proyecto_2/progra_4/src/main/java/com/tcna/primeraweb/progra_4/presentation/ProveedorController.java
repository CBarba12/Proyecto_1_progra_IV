package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ProveedorEntity proveedor,Model model){

        model.addAttribute("proveedor",proveedor);
        model.addAttribute("accion","/ProveedorController/editar"+id);

        return "formulario";
    }



    @PostMapping("/editar/{id}")
    public String actualizarProveedores(@PathVariable String id, @ModelAttribute ProveedorEntity proveedor){
     proveedorService.actualizarProveedro(id,proveedor);

     return "redirect:/ProveedorController/Listadeproveedores";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable String id, Model model){
        try {
            proveedorService.eliminarProveedor(id);
            return "redirect:/ProveedorController/Listadeproveedores";
        } catch (DataIntegrityViolationException e) {
            // Captura la excepción y agrega un mensaje de error al modelo
            model.addAttribute("error", "No se puede eliminar este proveedor porque hay clientes asociados a él.");
            return "redirect:/ProveedorController/Listadeproveedores";
        }
    }





}
