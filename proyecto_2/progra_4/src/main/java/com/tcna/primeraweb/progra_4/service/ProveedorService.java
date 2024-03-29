package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ProveedorService {


    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;






    //-------------------------------------listar-------------------------------------------------------------
    public List<ProveedorEntity> ObtenerProveedores() {
        return proveedorRepository.findAll();
    }


    public List<ProductoEntity> ObtenerProductos() {
        return productoRepository.findAll();
    }

    public List<FacturaEntity> ObtenerFacturas() {
        return facturaRepository.findAll();
    }

    public List<ClienteEntity> ObtenerCliente() {
        return clienteRepository.findAll();
    }



    // ----------------------crear entidades --------------------------------------------------

    public ProveedorEntity crearProveedores(ProveedorEntity persona) {

        return proveedorRepository.save(persona);
    }
    public ProductoEntity crearProductos(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public FacturaEntity crearFactura(FacturaEntity factura) {
        return facturaRepository.save(factura);
    }

    public ClienteEntity crearCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

//------------------------------------------------- contar numero de entidades

    public Long ContarProveedores() {
        return proveedorRepository.count();
    }
    public Long ContarProductos() {
        return productoRepository.count();
    }
    public Long ContarFactura() {
        return facturaRepository.count();
    }
    public Long ContarClientes() {
        return clienteRepository.count();
    }







}
