package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
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


    public List<ClienteEntity> ObtenerCliente() {
        return clienteRepository.findAll();
    }




    //--------------------------------------------------------------------------

    public ClienteEntity crearCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }


    public List<ClienteEntity> obtenerClientesPorProveedor(String id_proveedor) {
        return  clienteRepository.findByProveedorId(id_proveedor);
    }
    public void actualizarCliente(ClienteEntity cliente) {
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }


}
