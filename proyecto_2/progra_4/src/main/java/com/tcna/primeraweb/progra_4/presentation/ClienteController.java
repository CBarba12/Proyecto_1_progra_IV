package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
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
        model.addAttribute("id_proveedor", ID); // Añade esta línea

        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(ID);


        if (clientesProveedor != null) {
            model.addAttribute("clientesProveedor", clientesProveedor);
        }


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
        cle.setTipoCliente("tipoCliente"); // establece el tipoCliente aquí
        cle.setClienteId("clienteId"); // establece el clienteId aquí
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

        model.addAttribute("mensaje", "Se registró exitosamente un cliente");

        return "listarClientes";
    }
    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable("id") String id, Model model, HttpSession session) {
        List<ClienteEntity> clientes = clienteService.ObtenerCliente();

        ClienteEntity cliente = clientes.stream().filter(c -> c.getClienteId() == id).findFirst().orElse(null);
        String Provedor= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",Provedor);
        if (cliente != null) {

            model.addAttribute("cliente", cliente);

            return "formularioEditarCliente";
        } else {
            return "redirect:/ClienteController/ListadeClientes";
        }
    }

    @PostMapping("/editCliente")
    public String guardarEditarCliente(@ModelAttribute ClienteEntity cliente, Model model, HttpSession session){
        String Provedor= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",Provedor);
        cliente.setProveedorId(Provedor);
        clienteService.actualizarCliente(cliente);
        return "redirect:/ClienteController/ListadeClientes";
    }

}
