package com.tcna.primeraweb.progra_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;

import java.util.List;


@Component
public class     HaciendaStub {

    @Autowired
    private ProveedorService proveedorService;

    public boolean validarRegistroProveedor(ProveedorEntity proveedor) {
        List<ProveedorEntity> proveedoresRegistrados = proveedorService.ObtenerProveedores();

        for (ProveedorEntity proveedorRegistrado : proveedoresRegistrados) {
            if (proveedorRegistrado.getIdProveedor().equals(proveedor.getIdProveedor())) {
                return false;
            }
        }
        return true;
    }
}