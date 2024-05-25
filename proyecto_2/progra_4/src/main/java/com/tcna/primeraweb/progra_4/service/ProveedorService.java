package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                byte adminValue = proveedor.getAdmin().byteValue();
            }else {
                noAdminProveedores.add(proveedor);
            }
        }

        return noAdminProveedores;
    }

    public ProveedorEntity crearProveedores(ProveedorEntity persona) {
        var encoder = new BCryptPasswordEncoder();
        persona.setContrasena("{bcrypt}"+encoder.encode(persona.getContrasena()));
        return proveedorRepository.save(persona);
    }
    

  

//------------------------------------------------- contar numero de entidades

    public List<ProductoEntity> cliente_de_proveedor() {

        return productoRepository.findAll();
    }


    public boolean actualizarProveedor(ProveedorEntity provedor) {
        if(proveedorRepository.existsById(provedor.getIdProveedor())){
            proveedorRepository.save(provedor);
            return true;
        }
        return false;
    }




    public boolean verificarEmailPaswor(String numeroIdentificacion, String contrasena) {
        ProveedorEntity p = proveedorRepository.findByIdProveedorAndContrasena(numeroIdentificacion, contrasena);
        if (p != null) {
            // Verificar si el estado del proveedor es "En espera"
            if ("En espera".equals(p.getEstado())) {
                // El proveedor está en espera, no permitir el inicio de sesión
                return false;
            }
            // El proveedor no está en espera, permitir el inicio de sesión
            return true;
        }
        // El proveedor no existe
        return false;
    }

    public ProveedorEntity obtenerProveedorPorId(String proveedorId) {
        return proveedorRepository.findById(proveedorId).orElse(null);
    }



    public void eliminarProveedor(String id) {
        proveedorRepository.deleteById(id);
    }

    public boolean existeProveedor(String idProveedor) {
        return proveedorRepository.existsById(idProveedor);
    }
}

