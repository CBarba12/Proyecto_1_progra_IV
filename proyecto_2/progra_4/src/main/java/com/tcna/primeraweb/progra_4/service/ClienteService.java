package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ClienteService {

    @Getter
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public  void actualizarCliente(String id, ClienteEntity cliente) {


        ClienteEntity prove=clienteRepository.findById(id).orElse(null);

        if(prove !=null ){
            assert cliente != null;
            prove.setNombre(cliente.getNombre());
            prove.setCorreoElectronico(cliente.getCorreoElectronico());
            prove.setDireccion(cliente.getDireccion());
            prove.setTipoCliente(cliente.getTipoCliente());

             clienteRepository.save(prove);
        }





    }






    public List<ClienteEntity> ObtenerCliente() {
        return clienteRepository.findAll();
    }




    //--------------------------------------------------------------------------

    public ClienteEntity crearCliente(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }


    public List<ClienteEntity> obtenerClientesPorProveedor(String id_proveedor) {
        return  clienteRepository.findByProveedorId(id_proveedor);
    }
    public void actualizarCliente(ClienteEntity cliente) {
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }


    public ClienteEntity obtenerClienteId(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

}
