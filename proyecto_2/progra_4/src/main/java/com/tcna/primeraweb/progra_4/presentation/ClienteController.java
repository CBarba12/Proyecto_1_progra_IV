package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/ClienteController")
public class ClienteController {

    @Autowired
    private ProveedorService proveedorService;
    //@Autowired
   // private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    @GetMapping("/Listadeclientes") // Añade esta línea para mapear el método a la URL
    public String listarClientes(Model model, HttpSession session) {


        return "listarClientes";
    }
    @GetMapping("/RegistroCliente")
    public String showForm(Model model, HttpSession session) {
        ClienteEntity cliente = new ClienteEntity();
        String ID= (String) session.getAttribute("proveedorId");
        cliente.setProveedorId(ID);
        cliente.setClienteId("clienteId"); // establece el clienteId aquí
        model.addAttribute("cliente", cliente);
        return "registroCliente";
    }
    /*@PostMapping("/registrar")
    public String submitForm(@ModelAttribute("cliente") @Valid ClienteEntity cliente, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "registroCliente";
        }
        clienteService.crearCliente(cliente);
        model.addAttribute("mensaje", "Se registró exitosamente un cliente");
        return "redirect:/ClienteController/Listadeclientes";
    }*/


    //-------------------------------------
    @GetMapping("/nuevocliente")
    public String MostrarFormularioNuevoCliente(HttpSession session,Model model){

        ClienteEntity cle=new ClienteEntity();
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        cle.setProveedorId(ID);
        cle.setTipoCliente("tipoCliente");
        cle.setClienteId("clienteId");
        model.addAttribute("cliente",cle);
        return "registroCliente";
    }
    @PostMapping("/nuevo")
    public String guardarNuevoCliente(@ModelAttribute ClienteEntity cliente,HttpSession session,Model model){


        return "listarClientes";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ClienteEntity cliente,Model model){


       //ClienteEntity p= clienteService.obtenerClienteId(id);

            return "redirect:/ClienteController/listarClientes";



    }
    @PostMapping("/editar/{id}")
    public String actualizarProveedores(@PathVariable String id, @ModelAttribute  ClienteEntity cliente){



        return "redirect:/ClienteController/Listadeclientes";
    }

}