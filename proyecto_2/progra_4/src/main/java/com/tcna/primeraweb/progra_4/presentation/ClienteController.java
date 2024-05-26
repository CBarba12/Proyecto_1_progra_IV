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


    @GetMapping("/Listadeclientes/{idproveedor}")
    public List<ClienteEntity> listarClientes(@PathVariable("idproveedor") String proveedor) {
        if(proveedorService.existeProveedor(proveedor)) {
            List<ClienteEntity> clientesProveedor = proveedoresService.obtenerClientesPorProveedor(proveedor);
            if (clientesProveedor != null) {
                return clientesProveedor;
            }
        }
        return null;
    }

    @PostMapping("/NewCliente/{idproveedor}")
    public ResponseEntity<ClienteEntity> guardarNuevoCliente(@RequestBody ClienteEntity cliente, @PathVariable("idproveedor") String idproveedor) {
        {
            try {
                if (proveedorService.existeProveedor(idproveedor)) { // Verifica si el proveedor existe
                    ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(idproveedor);
                    if (!clienteService.existeCliente(cliente.getClienteId())) { // Verifica si el cliente existe y si no existe lo crea
                        clienteService.crearCliente(cliente);
                    }
                    if (proveedoresService.crear(cliente, proveedor)) { // Verifica si se pudo crear la relación entre el cliente y el proveedor
                        return ResponseEntity.ok(cliente);
                    }
                }
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<ClienteEntity> actualizarCliente(@RequestBody ClienteEntity cliente){
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

    @GetMapping("/existe/{idcliente}/{idproveedor}")
    public boolean existeCliente(@PathVariable("idcliente") String idcliente, @PathVariable("idproveedor") String idproveedor){
        return proveedoresService.existeCliente(idcliente, idproveedor);
    }

    @PostMapping("/delete/{idcliente}/{idproveedor}")
    public ResponseEntity<ClienteEntity> eliminarCliente(@PathVariable("idcliente") String idcliente, @PathVariable("idproveedor") String idproveedor){
        try {
            if(proveedoresService.existeCliente(idcliente, idproveedor)){
                proveedoresService.eliminarCliente(idcliente, idproveedor);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}