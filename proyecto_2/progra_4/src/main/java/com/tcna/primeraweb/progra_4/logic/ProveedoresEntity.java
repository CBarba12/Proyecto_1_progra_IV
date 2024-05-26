package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class ProveedoresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "id_cliente", nullable = false)
    private String idCliente;

   @Basic
    @Column(name = "id_proveedor", nullable = false)
    private String idProveedor;

    public ProveedoresEntity() {    }

    public ProveedoresEntity(String idCliente, String idProveedor) {
        this.idCliente = idCliente;
        this.idProveedor = idProveedor;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIdCliente() { return idCliente; }

    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getIdProveedor() { return idProveedor; }

    public void setIdProveedor(String idProveedor) { this.idProveedor = idProveedor; }
}