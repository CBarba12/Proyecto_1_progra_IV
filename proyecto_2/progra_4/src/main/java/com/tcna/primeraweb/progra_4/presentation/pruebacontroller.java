package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ProveedorDTO;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.HaciendaStub;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Prueba")
public class pruebacontroller {


    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private com.tcna.primeraweb.progra_4.service.HaciendaStub HaciendaStub;

    @GetMapping("/listar")
    public List<ProveedorEntity> ver(){
        List<ProveedorEntity> proveedores=  proveedorService.ObtenerProveedores();
        return proveedores;
    }



    @PostMapping("/listar")
    public ProveedorEntity crear(@RequestBody ProveedorDTO proveedorDTO) {
        // Aquí puedes hacer cualquier validación o transformación necesaria
        // por ejemplo, convertir ProveedorDTO a ProveedorEntity si es necesario
        ProveedorEntity proveedorEntity = convertirProveedorDTOaProveedorEntity(proveedorDTO);
        return proveedorService.crearProveedores(proveedorEntity);
    }

    private ProveedorEntity convertirProveedorDTOaProveedorEntity(ProveedorDTO proveedorDTO) {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setIdProveedor(proveedorDTO.getIdProveedor());
        proveedorEntity.setNombre(proveedorDTO.getNombre());
        proveedorEntity.setCorreoElectronico(proveedorDTO.getCorreoElectronico());
        proveedorEntity.setContrasena(proveedorDTO.getContrasena());
        proveedorEntity.setEstado(proveedorDTO.getEstado());
        proveedorEntity.setAdmin(proveedorDTO.isAdmin() ? (byte) 1 : (byte) 0);
        proveedorEntity.setTelefono(proveedorDTO.getTelefono());
        proveedorEntity.setDireccion(proveedorDTO.getDireccion());
        proveedorEntity.setTipoProveedor(proveedorDTO.getTipoProveedor());
        proveedorEntity.setActividadComercial(proveedorDTO.getActividadComercial());

        // Setear otros campos según sea necesario
        return proveedorEntity;
    }



}
