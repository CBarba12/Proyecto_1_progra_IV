package com.tcna.primeraweb.progra_4.service;


import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.ProveedoresRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedoresEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresService {
    @Getter
    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public boolean crear(ClienteEntity cliente, ProveedorEntity proveedor){ // Para crearlo no debe existir
        List<ProveedoresEntity> proveedores = proveedoresRepository.findAll();
        for (ProveedoresEntity proveedor1 : proveedores) {
            if (proveedor1.getIdCliente().getClienteId().equals(cliente.getClienteId()) && proveedor1.getIdProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
                return false;
            }
        }
        return proveedoresRepository.save(new ProveedoresEntity(cliente, proveedor)) != null;
    }

    public List<ClienteEntity> obtenerClientesPorProveedor(String idProveedor) {
        List<ProveedoresEntity> proveedores = proveedoresRepository.findAll();
        List<ClienteEntity> clientes = null;
        for (ProveedoresEntity proveedor : proveedores) {
            if (proveedor.getIdProveedor().getIdProveedor().equals(idProveedor)) {
                clientes.add(clienteRepository.findById(proveedor.getIdCliente().getClienteId()).get());
            }
        }
        return clientes;
    }

    public boolean existeCliente(String idcliente, String idproveedor) {
        List<ProveedoresEntity> proveedores = proveedoresRepository.findAll();
        for (ProveedoresEntity proveedor : proveedores) {
            if (proveedor.getIdCliente().getClienteId().equals(idcliente) && proveedor.getIdProveedor().getIdProveedor().equals(idproveedor)) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(String idcliente, String idproveedor) {
        List<ProveedoresEntity> proveedores = proveedoresRepository.findAll();
        for (ProveedoresEntity proveedor : proveedores) {
            if (proveedor.getIdCliente().getClienteId().equals(idcliente) && proveedor.getIdProveedor().getIdProveedor().equals(idproveedor)) {
                proveedoresRepository.delete(proveedor);
                return true;
            }
        }
        return false;
    }
}
