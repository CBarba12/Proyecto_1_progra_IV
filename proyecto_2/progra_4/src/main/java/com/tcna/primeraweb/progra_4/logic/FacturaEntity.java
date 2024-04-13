package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "factura", schema = "proyecto_1")
public class FacturaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "factura_id")
    private int facturaId;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "cliente")
    private String cliente;
    @Basic
    @Column(name = "proveedor")
    private String proveedor;


    public FacturaEntity(int facturaId, Date fecha, Double total, String cliente, String proveedor) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    public FacturaEntity() {

    }


    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }




}
