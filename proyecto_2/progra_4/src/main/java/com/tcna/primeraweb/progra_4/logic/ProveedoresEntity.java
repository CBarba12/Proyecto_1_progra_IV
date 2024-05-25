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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity idCliente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity idProveedor;

    public ProveedoresEntity() {    }

    public ProveedoresEntity(ClienteEntity idCliente, ProveedorEntity idProveedor) {
        this.idCliente = idCliente;
        this.idProveedor = idProveedor;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public ClienteEntity getIdCliente() { return idCliente; }

    public void setIdCliente(ClienteEntity idCliente) { this.idCliente = idCliente; }

    public ProveedorEntity getIdProveedor() { return idProveedor; }

    public void setIdProveedor(ProveedorEntity idProveedor) { this.idProveedor = idProveedor; }
}