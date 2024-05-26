package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ClienteService {

    @Getter
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public  boolean actualizarCliente(ClienteEntity cliente) {
        if(clienteRepository.existsById(cliente.getClienteId())){
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }






    public List<ClienteEntity> ObtenerCliente() {
        return clienteRepository.findAll();
    }




    //--------------------------------------------------------------------------

    public ClienteEntity crearCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

// Este metodo se debe cambiar o no va aqui
//    public List<ClienteEntity> obtenerClientesPorProveedor(String id_proveedor) {
//        return  clienteRepository.findByProveedorId(id_proveedor);
//    }


    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }


    public ClienteEntity obtenerClienteId(String id) {
        return clienteRepository.findById(id).orElse(null);
    }


    public boolean existeCliente(String clienteId) {
        return clienteRepository.existsById(clienteId);
    }

    public ClienteEntity obtenerClientePorId(String cliente) {
        return clienteRepository.findById(cliente).orElse(null);
    }
}
