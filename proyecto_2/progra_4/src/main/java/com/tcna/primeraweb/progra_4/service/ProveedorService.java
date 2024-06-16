package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ProveedorService {

/*
    @Getter
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

**/
    //-------------------------------------listar-------------------------------------------------------------
    public List<ProveedorEntity> ObtenerProveedores()
    {/*
        List<ProveedorEntity> proveedores = proveedorRepository.findAll();

        List<ProveedorEntity> noAdminProveedores = new ArrayList<>();

        for (ProveedorEntity proveedor : proveedores) {

            if (proveedor.getAdmin() == 1) {
                byte adminValue = proveedor.getAdmin().byteValue();
            }else {
                noAdminProveedores.add(proveedor);
            }
        }
*/

        return null;
    }

    public ProveedorEntity crearProveedores(ProveedorEntity persona) {

        return null;
    }
    

  

//------------------------------------------------- contar numero de entidades

    public List<ProductoEntity> cliente_de_proveedor() {

        return null;
    }


    public ProveedorEntity actualizarProveedor(String id, ProveedorEntity provedor) {

        return null;
    }




    public boolean verificarEmailPaswor(String numeroIdentificacion, String contrasena) {

        return false;
    }

    public ProveedorEntity obtenerProveedorPorId(String proveedorId) {
        return null;
    }



    public void eliminarProveedor(String id) {


    }
}

