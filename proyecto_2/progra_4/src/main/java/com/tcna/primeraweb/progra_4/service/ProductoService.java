package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ProductoService {


/*
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


*/
    public List<ProductoEntity> ObtenerProductos() {

        return null;
    }


    public ProductoEntity crearProductos(ProductoEntity producto) {

        return null;
    }

    public Long ContarProductos() {

        return 6L;
    }

    public List<ProductoEntity> obtenerProductoPorProveedor(String id) {
        return  null;
    }



    public void actualizarProducto(ProductoEntity producto) {

        //productoRepository.save(producto);
    }


    public void eliminarProducto(int id) {


       // productoRepository.deleteById(id);
    }
}
