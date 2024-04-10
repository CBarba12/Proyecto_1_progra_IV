package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.Collections;
import java.util.List;
@org.springframework.stereotype.Service
public class ProductoService {



    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    public List<ProductoEntity> ObtenerProductos() {
        return productoRepository.findAll();
    }


    public ProductoEntity crearProductos(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public Long ContarProductos() {
        return productoRepository.count();
    }

}
