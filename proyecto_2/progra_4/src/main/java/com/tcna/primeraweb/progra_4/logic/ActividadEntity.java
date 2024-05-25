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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Proveedor")
    private ProveedorEntity idProveedor;

    @Size(max = 15)
    @Column(name = "nombre_Actividad", length = 15)
    private String nombreActividad;


    public ActividadEntity() {   }

    public ActividadEntity(ProveedorEntity idProveedor, String nombreActividad) {
        this.idProveedor = idProveedor;
        this.nombreActividad = nombreActividad;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public ProveedorEntity getIdProveedor() { return idProveedor; }

    public void setIdProveedor(ProveedorEntity idProveedor) { this.idProveedor = idProveedor; }

    public String getNombreActividad() { return nombreActividad; }

    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }
}