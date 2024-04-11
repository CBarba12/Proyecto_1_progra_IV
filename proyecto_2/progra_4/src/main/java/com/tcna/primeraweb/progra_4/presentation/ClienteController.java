package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.persistence.Id;
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

        String ID= (String) session.getAttribute("id_proveedor");

        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(ID);


        if (clientesProveedor != null) {
            model.addAttribute("clientesProveedor", clientesProveedor);
        }


        return "listarClientes";
    }






    //-------------------------------------
    @GetMapping("/nuevocliente")
    public String MostrarFormularioNuevoCliente(@RequestParam("proveedorId") String numeroIdentificacion,Model model){

        ClienteEntity cle=new ClienteEntity();
        cle.setProveedorId(numeroIdentificacion);

        model.addAttribute("cliente",cle);


        return "formulariocliente";
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




}
