package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.*;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProveedoresService proveedoresService;

    // Modificar ya que los clientes asociados a un proveedor se va a manejar diferente
//    @GetMapping("/Listadeclientes")
//    public List<ClienteEntity> listarClientes(@RequestBody ProveedorEntity proveedor) {
//        List<ClienteEntity> clientesProveedor =clienteService. obtenerClientesPorProveedor(proveedor.getIdProveedor());
//        if (clientesProveedor != null) {
//            return clientesProveedor;
//        }
//        return null;
//    }

    @PostMapping("/NewCliente")
    public ResponseEntity<ClienteEntity> guardarNuevoCliente(@RequestBody ClienteEntity cliente, @RequestBody ProveedorEntity proveedor) {
        try {
            if(proveedorService.existeProveedor(proveedor.getIdProveedor())){ // Verifica si el proveedor existe
                if(!clienteService.existeCliente(cliente.getClienteId())){ // Verifica si el cliente existe y si no existe lo crea
                    clienteService.crearCliente(cliente);
                }
                if(proveedoresService.crear(cliente, proveedor)){ // Verifica si se pudo crear la relación entre el cliente y el proveedor
                    return ResponseEntity.ok(cliente);
                }
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/editar")
    public ResponseEntity<ClienteEntity> actualizarProveedores(@RequestBody ClienteEntity cliente){
        try {
            if(clienteService.actualizarCliente(cliente)){
                return ResponseEntity.ok(cliente);
            }else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // Creo que no se ocupa
//    @GetMapping("/RegistroCliente")
//    public String showForm(Model model, HttpSession session) {
//        ClienteEntity cliente = new ClienteEntity();
//        String ID= (String) session.getAttribute("proveedorId");
//        cliente.setProveedorId(ID);
//        cliente.setClienteId("clienteId"); // establece el clienteId aquí
//        model.addAttribute("cliente", cliente);
//        return "registroCliente";
//    }



    //-------------------------------------

    // Si es para mostrar el formulario de registro de un nuevo cliente, creo que no se ocupa
//    @GetMapping("/nuevocliente")
//    public String MostrarFormularioNuevoCliente(HttpSession session,Model model){
//
//        ClienteEntity cle=new ClienteEntity();
//        String ID= (String) session.getAttribute("id_proveedor");
//        model.addAttribute("id_proveedor",ID);
//        cle.setProveedorId(ID);
//        cle.setTipoCliente("tipoCliente");
//        cle.setClienteId("clienteId");
//        model.addAttribute("cliente",cle);
//        return "registroCliente";
//    }




// Creo que no se ocupa
//    @GetMapping("/editar/{id}")
//    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ClienteEntity cliente,Model model){
//
//
//       ClienteEntity p= clienteService.obtenerClienteId(id);
//
//
//        if (p != null) {
//
//            model.addAttribute("Cliente_editar", p);
//            model.addAttribute("editar_CLIENTE","/ClienteController/editar"+id);
//            return "FormularioEditarCliente";
//        }else {
//            return "redirect:/ClienteController/listarClientes";
//        }
//
//
//    }




}