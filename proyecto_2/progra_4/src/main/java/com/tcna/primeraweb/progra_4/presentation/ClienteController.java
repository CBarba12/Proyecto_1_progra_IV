package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
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
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    @GetMapping("/Listadeclientes") // Añade esta línea para mapear el método a la URL
    public String listarClientes(Model model, HttpSession session) {
        // Verificar si hay clientes asociados al proveedor en la sesión
        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }
        // Obtener todos los clientes y agregarlos al modelo
        List<ClienteEntity> todosClientes = clienteService.ObtenerCliente();
        model.addAttribute("todosClientes", todosClientes);
        return "listarClientes"; // Suponiendo que el nombre de la vista es listarClientes.html
    }

    @GetMapping("/Clientes") // Añade esta línea para mapear el método a la URL
    public String vectorClientes(Model model){

        List<ClienteEntity> cliente=clienteService.ObtenerCliente();

        model.addAttribute("clientes", cliente);
        return "listarClientes";
    }



    //-------------------------------------
    @GetMapping("/nuevocliente")
    public String MostrarFormularioNuevoCliente(@RequestParam("proveedorId") String numeroIdentificacion,Model model){
        ClienteEntity cle=new ClienteEntity();

        cle.setProveedor_id(numeroIdentificacion);

        model.addAttribute("cliente",cle);
        model.addAttribute("accion2","/ClienteController/nuevo");

        return "formulariocliente";
    }
    @PostMapping("/nuevo")
    public String guardarNuevoCliente(@ModelAttribute ClienteEntity cliente){

        clienteService.crearCliente(cliente);

        return "listarClientes";
    }




}
