package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actividad")
public class ActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "id_Proveedor", nullable = false, length = 15)
    private String idProveedor;

    @Size(max = 15)
    @Column(name = "nombre_Actividad", length = 15)
    private String nombreActividad;


    public ActividadEntity() {   }

    public ActividadEntity(String idProveedor, String nombreActividad) {
        this.idProveedor = idProveedor;
        this.nombreActividad = nombreActividad;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIdProveedor() { return idProveedor; }

    public void setIdProveedor(String idProveedor) { this.idProveedor = idProveedor; }

    public String getNombreActividad() { return nombreActividad; }

    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }
}