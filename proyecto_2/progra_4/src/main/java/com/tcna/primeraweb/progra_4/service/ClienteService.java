package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public ClienteEntity crearCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }


    public List<ClienteEntity> obtenerClientesPorProveedor(String contrasena) {

        return  clienteRepository.findByProveedorId(contrasena);
    }
}
