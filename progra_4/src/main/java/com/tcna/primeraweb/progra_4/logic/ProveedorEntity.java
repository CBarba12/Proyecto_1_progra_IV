package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Proveedor", schema = "proyecto_1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorEntity {

    @Id
    private String idProveedor;
    @Basic
    @Column (name="Nombre")
    private String nombre;

    @Basic
    @Column (name="correoElectronico")
    private String correoElectronico;

    private String contrasena;

    private boolean activo;
    private Integer telefono;
    private String direccion;
    private String tipoProveedor;




}
