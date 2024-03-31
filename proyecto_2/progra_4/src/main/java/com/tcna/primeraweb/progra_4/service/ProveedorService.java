package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.Collections;
import java.util.List;
@org.springframework.stereotype.Service
public class ProveedorService {


    @Getter
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




    public List<ClienteEntity> clienteproveedor(){

        ProveedorEntity proveedor = proveedorRepository.findById("PROV2").orElse(null);
        if (proveedor == null) {
            // El proveedor no existe, puedes manejar este caso según sea necesario
            return Collections.emptyList();
        }

        return clienteRepository.findByProveedorId("PROV2");

       }
       

    // ----------------------crear entidades --------------------------------------------------

    public ProveedorEntity crearProveedores(ProveedorEntity persona) {

        return proveedorRepository.save(persona);
    }
    

  

//------------------------------------------------- contar numero de entidades

    public Long ContarProveedores() {
        return proveedorRepository.count();
    }
  
   
   





    public List<ProductoEntity> cliente_de_proveedor() {


        return productoRepository.findAll();
    }




}
