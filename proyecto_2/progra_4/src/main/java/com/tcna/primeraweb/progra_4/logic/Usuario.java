package com.tcna.primeraweb.progra_4.logic;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private String nombre;
    private String password;
    private String email;
    private String rol;
    private String id;
}
