package com.tcna.primeraweb.project_progra.service;


import com.tcna.primeraweb.project_progra.data.ProveedorRepository;
import com.tcna.primeraweb.project_progra.logic.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ProveedorService {


    @Autowired
    private ProveedorRepository proveedorRepository;
    public List<ProveedorEntity> obtenerProveedores() {

        return proveedorRepository.findAll();
    }
    public ProveedorEntity crearPersona(ProveedorEntity persona) {

        return proveedorRepository.save(persona);
    }
    public Long ContarProveedores() {
        return proveedorRepository.count();
    }

}
