package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    public List<ProveedorEntity> ObtenerProveedores()
    {
        List<ProveedorEntity> proveedores = proveedorRepository.findAll();

        List<ProveedorEntity> noAdminProveedores = new ArrayList<>();

        for (ProveedorEntity proveedor : proveedores) {

            if (proveedor.getAdmin() == 1) {

            }else {
                noAdminProveedores.add(proveedor);
            }
        }

        return noAdminProveedores;
    }

    public ProveedorEntity crearProveedores(ProveedorEntity persona) {

        return proveedorRepository.save(persona);
    }
    

  

//------------------------------------------------- contar numero de entidades

    public List<ProductoEntity> cliente_de_proveedor() {

        return productoRepository.findAll();
    }


    public ProveedorEntity actualizarProveedor(String id, ProveedorEntity provedor) {

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

    public boolean verificarEmailPaswor(String numeroIdentificacion, String contrasena) {

        ProveedorEntity p=proveedorRepository.findByIdProveedorAndContrasena(numeroIdentificacion,contrasena);
          return p != null;
    }

    public ProveedorEntity obtenerProveedorPorId(String proveedorId) {
        return proveedorRepository.findById(proveedorId).orElse(null);
    }



}

