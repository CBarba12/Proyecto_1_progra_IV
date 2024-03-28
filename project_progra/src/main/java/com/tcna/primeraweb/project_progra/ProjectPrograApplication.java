package com.tcna.primeraweb.project_progra;

import com.tcna.primeraweb.project_progra.data.ClienteRepository;
import com.tcna.primeraweb.project_progra.data.ProveedorRepository;
import com.tcna.primeraweb.project_progra.logic.ClienteEntity;
import com.tcna.primeraweb.project_progra.logic.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProjectPrograApplication implements CommandLineRunner {


    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectPrograApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------------------------------------------------------");

        proveedorRepository.save(new ProveedorEntity("PROV6", "Proveedor Uno", "proveedor1@example.com", "contrasena1", (byte) 1, 1234567890, "Dirección 1","Persona Física","prueba"));


        clienteRepository.save(new ClienteEntity("801100977","Cesar","san jose","fisico","prueba"));



        System.out.println("Numero total de la tabla : " +proveedorRepository.count());


        List<ProveedorEntity> Proveedor=proveedorRepository.findAll();
        List<ClienteEntity> persona2=clienteRepository.findAll();

        Proveedor.forEach(p-> System.out.println("Nombre de proveedor "+p.getNombre()));
        persona2.forEach(p-> System.out.println("Nombre de proveedor "+p.getNombre()));
    }
}

