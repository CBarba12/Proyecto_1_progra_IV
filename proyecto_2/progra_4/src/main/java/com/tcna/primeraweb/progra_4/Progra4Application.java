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
         /*               .requestMatchers("/api/LoginController/login").permitAll()
                        .requestMatchers("/api/LoginController/logout").authenticated()
                        .requestMatchers("/api/ProveedorController/Listadeproveedores").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/ProveedorController/estado").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/ProveedorController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/ClienteController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/FacturaController/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/ProductoController/**").hasAnyAuthority("ROLE_USER")*/
                        .requestMatchers("/**").permitAll()
                )
                .exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .csrf().disable()
                .build();
        return chain;
    }

}
