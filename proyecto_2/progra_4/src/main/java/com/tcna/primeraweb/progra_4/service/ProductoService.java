package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;

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

    public List<ProductoEntity> obtenerProductoPorProveedor(String id) {
        return  productoRepository.findByProveedorId(id);
    }



    public boolean actualizarProducto(ProductoEntity producto) {
        if(productoRepository.existsById(producto.getProductoId())) {
            productoRepository.save(producto);
            return true;
        }
        return false;
    }


    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    public ProductoEntity obtenerProductoPorId(int idProducto) {
        return productoRepository.findById(idProducto).get();
    }
}
