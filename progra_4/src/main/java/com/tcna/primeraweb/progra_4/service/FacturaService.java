package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class FacturaService {

    @Getter
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;



    public List<FacturaEntity> ObtenerFacturas() {
        return facturaRepository.findAll();
    }

    public FacturaEntity crearFactura(FacturaEntity factura) {
        return facturaRepository.save(factura);
    }
    public Long ContarFactura() {
        return facturaRepository.count();
    }



}
