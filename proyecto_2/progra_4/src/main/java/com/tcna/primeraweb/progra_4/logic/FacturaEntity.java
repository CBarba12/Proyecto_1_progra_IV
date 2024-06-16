package com.tcna.primeraweb.progra_4.logic;



import java.sql.Date;


public class FacturaEntity {

    private int facturaId;

    private Date fecha;

    private Double total;

    private String cliente;

    private String proveedor;

    private int id_producto;

    private int cantidad;


    public FacturaEntity(int facturaId, Date fecha, Double total, String cliente, String proveedor, int id_producto, int cantidad) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.proveedor = proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
