package com.tcna.primeraweb.progra_4;

import com.tcna.primeraweb.progra_4.data.ClienteRepository;
import com.tcna.primeraweb.progra_4.data.FacturaRepository;
import com.tcna.primeraweb.progra_4.data.ProductoRepository;
import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

@SpringBootApplication
public class Progra4Application  {


    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;



    public static void main(String[] args) {

        SpringApplication.run(Progra4Application.class, args);
    }

/*
    @Override
    public void run(String... args) throws Exception {

        LocalDate fechaActual = LocalDate.now();
        Date date = java.sql.Date.valueOf(fechaActual);




        proveedorRepository.save(new ProveedorEntity("PROV7", "Proveedor Uno", "proveedor1@example.com", "contrasena1", true, 1234567890, "Dirección 1","Persona Física"));
        proveedorRepository.save(new ProveedorEntity("PROV8", "Proveedor 8", "proveedor8@example.com", "contrasena8", false, 1234567890, "Dirección 8","Persona juridica"));

        clienteRepository.save(new ClienteEntity("8888","hh","r","gh","fg","PROV8"));
        productoRepository.save(new ProductoEntity(23,"apple","tasty",20000,"producto","PROV8"));

        facturaRepository.save( new FacturaEntity(23,date, 20000.0f, "ClienteEjemplo"));


        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("Numero total de la tabla : " +proveedorRepository.count());


        List<ProveedorEntity> Proveedor=proveedorRepository.findAll();

        Proveedor.forEach(p-> System.out.println("Nombre de proveedor "+p.toString()));

    }

 */
}
