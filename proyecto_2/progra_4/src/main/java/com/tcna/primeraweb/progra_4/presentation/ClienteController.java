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
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ClienteController")
public class ClienteController {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;

    // Se ocupa que venga el id del proveedor para mostrar los clientes asociados
    @GetMapping("/Listadeclientes") // Añade esta línea para mapear el método a la URL
    public List<ClienteEntity> listarClientes(@RequestBody ProveedorEntity proveedor) {
        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(proveedor.getIdProveedor());
        if (clientesProveedor != null) {
            return clientesProveedor;
        }
        return null;
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

        String Provedor= (String) session.getAttribute("id_proveedor");
        cliente.setProveedorId(Provedor);

        if ("ninguna".equals(cliente.getTipoCliente())) {
            /*redirectAttributes.addFlashAttribute("error", "Por favor, seleccione un tipo de proveedor válido (Físico o Jurídico)");*/
            model.addAttribute("proveedor", cliente);
            model.addAttribute("mensaje", "Seleccione un tipo de proveedor");
            return "redirect:/ClienteController/nuevocliente";
        }

        if ("Físico".equals(cliente.getTipoCliente()) && cliente.getClienteId().length() != 9) {
            model.addAttribute("error", "El número de identificación de un cliente físico debe tener exactamente 9 dígitos.");
            return "redirect:/ClienteController/nuevocliente";
        }

        if ("Jurídico".equals(cliente.getTipoCliente()) && cliente.getClienteId().length() != 10) {
            model.addAttribute("mensaj", "El número de identificación de un cliente jurídico debe tener exactamente 10 dígitos.");
            return "redirect:/ClienteController/nuevocliente";
        }

        if (!cliente.getNombre().matches("[a-zA-Z ]+")) {
            model.addAttribute("erro", "El nombre solo puede contener letras y espacios.");
            return "redirect:/ClienteController/nuevocliente";
        }

        if(clienteService.obtenerClienteId(cliente.getClienteId())==null){
            clienteService.crearCliente(cliente);
        }else {
            return "redirect:/ClienteController/nuevocliente";
        }

        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(Provedor);



        if (clientesProveedor != null) {
            model.addAttribute("clientesProveedor", clientesProveedor);
        }

        model.addAttribute("mensaje", "Se registró exitosamente un cliente");

        return "listarClientes";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ClienteEntity cliente,Model model){


       ClienteEntity p= clienteService.obtenerClienteId(id);


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