package com.tcna.primeraweb.progra_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;

import java.util.List;


@Component
public class HaciendaStub {

    @Autowired
    private ProveedorService proveedorService;

    public boolean validarRegistroProveedor(ProveedorEntity proveedor) {
        // Obtener la lista de proveedores existentes
        List<ProveedorEntity> proveedoresRegistrados = proveedorService.ObtenerProveedores();

        // Verificar si el proveedor ya está registrado
        for (ProveedorEntity proveedorRegistrado : proveedoresRegistrados) {
            if (proveedorRegistrado.getCorreoElectronico().equals(proveedor.getCorreoElectronico())) {
                return false; // El proveedor ya está registrado, no permitir el registro
            }
        }
        return true; // El proveedor no está registrado, permitir el registro
    }
}