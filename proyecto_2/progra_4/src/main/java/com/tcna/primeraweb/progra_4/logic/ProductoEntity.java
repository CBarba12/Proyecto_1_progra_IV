package com.tcna.primeraweb.progra_4.logic;


import java.util.Objects;

public class ProductoEntity {


    private int productoId;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String tipoProducto;

    private String proveedorId;


    public ProductoEntity(String nombre, String descripcion, Double precio, String tipoProducto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }


    public ProductoEntity() {
    }




    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "productoId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", proveedorId='" + proveedorId + '\'' +
                '}';
    }
}
