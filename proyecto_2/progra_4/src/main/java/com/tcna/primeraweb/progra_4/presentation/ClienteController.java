package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/ClienteController")
public class ClienteController {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    @GetMapping("/Listadeclientes") // Añade esta línea para mapear el método a la URL
    public String listarClientes(Model model, HttpSession session) {

        String ID= (String) session.getAttribute("id_proveedor");

        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(ID);


        if (clientesProveedor != null) {
            model.addAttribute("clientesProveedor", clientesProveedor);
        }


        return "listarClientes";
    }
    @GetMapping("/RegistroCliente")
    public String showForm(Model model) {
        ClienteEntity cliente = new ClienteEntity();
        model.addAttribute("cliente", cliente);
        return "registroCliente";
    }
    /*@PostMapping("/registrar")
    public String submitForm(@Valid ClienteEntity cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registroCliente";
        }
        return "redirect:/success";
    }*/


    //-------------------------------------
    @GetMapping("/nuevocliente")
    public String MostrarFormularioNuevoCliente(HttpSession session,Model model){

        ClienteEntity cle=new ClienteEntity();
        String ID= (String) session.getAttribute("id_proveedor");

        cle.setProveedorId(ID);

        model.addAttribute("cliente",cle);


        return "registroCliente";
    }

    @PostMapping("/nuevo")
    public String guardarNuevoCliente(@ModelAttribute ClienteEntity cliente,HttpSession session,Model model){

        String Provedor= (String) session.getAttribute("id_proveedor");
        cliente.setProveedorId(Provedor);
        clienteService.crearCliente(cliente);

        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(Provedor);

        if (clientesProveedor != null) {
            model.addAttribute("clientesProveedor", clientesProveedor);
        }


        return "listarClientes";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ClienteEntity cliente,Model model){

       ClienteEntity p= clienteService.optenerClienteId(id);

        if (p != null) {

            model.addAttribute("Cliente_editar", p);
            model.addAttribute("editar_CLIENTE","/ClienteController/editar"+id);
            return "FormularioEditarCliente";
        }else {
            return "redirect:/ClienteController/listarClientes";
        }


    }
    @PostMapping("/editar/{id}")
    public String actualizarProveedores(@PathVariable String id, @ModelAttribute  ClienteEntity cliente){

        clienteService.actualizarCliente(id,cliente);

        return "redirect:/ClienteController/Listadeclientes";
    }

}
