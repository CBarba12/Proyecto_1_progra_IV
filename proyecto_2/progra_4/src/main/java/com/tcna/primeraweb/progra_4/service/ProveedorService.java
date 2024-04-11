package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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


        return proveedorRepository.findByActivoTrue();
    }



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

    public ProveedorEntity actualizarProveedro(String id, ProveedorEntity provedor) {

        ProveedorEntity prove=proveedorRepository.findById(id).orElse(null);

        if(provedor !=null ){
            assert prove != null;
            prove.setNombre(provedor.getNombre());
            prove.setCorreoElectronico(provedor.getCorreoElectronico());
            prove.setTelefono(provedor.getTelefono());
            prove.setDireccion(provedor.getDireccion());
            prove.setDireccion(provedor.getTipoProveedor());

            return proveedorRepository.save(prove);
        }


  return null;
    }

    public void eliminarProveedor(String id) {
        Optional<ProveedorEntity> proveedorOptional = proveedorRepository.findById(id);
        if (((Optional<?>) proveedorOptional).isPresent()) {
            ProveedorEntity proveedor = proveedorOptional.get();
            proveedor.setActivo(false);
            proveedorRepository.save(proveedor); // Guardar los cambios en la base de datos
        } else {
            throw new IllegalArgumentException("No se encontró ningún proveedor con el ID dado: " + id);
        }
    }




    public boolean verificarEmailPaswor(String numeroIdentificacion, String contrasena) {

        ProveedorEntity p=proveedorRepository.findByIdProveedorAndContrasena(numeroIdentificacion,contrasena);
          return p != null;
    }

    public ProveedorEntity obtenerProveedorPorId(String proveedorId) {
        return proveedorRepository.findById(proveedorId).orElse(null);
    }



}

