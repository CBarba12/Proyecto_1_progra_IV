package com.tcna.primeraweb.progra_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@SpringBootApplication
public class Progra4Application  {

    public static void main(String[] args) {

        SpringApplication.run(Progra4Application.class, args);
    }

    @Bean("securityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var chain = http
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/api/LoginController/login").permitAll()
                        .requestMatchers("/api/LoginController/logout").authenticated()
                        .requestMatchers("/api/ProveedorController/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/api/ClienteController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/FacturaController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/ProductoController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/**").permitAll()
                )
                .exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .csrf().disable()
                .build();
        return chain;
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
