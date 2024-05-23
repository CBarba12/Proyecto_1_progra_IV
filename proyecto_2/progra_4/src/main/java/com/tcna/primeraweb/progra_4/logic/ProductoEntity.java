package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "producto", schema = "proyecto_1")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment strategy
    @Column(name = "producto_id")
    private int productoId;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "precio")
    private Double precio;
    @Basic
    @Column(name = "tipo_producto")
    private String tipoProducto;
    @Basic
    @Column(name = "proveedor_id")
    private String proveedorId;
    @Basic
    @Column(name = "unidad")
    private String unidad;


    public ProductoEntity(String nombre, String descripcion, Double precio, String tipoProducto, String unidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.unidad = unidad;
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

    public String getUnidad() { return unidad; }

    public void setUnidad(String unidad) { this.unidad = unidad; }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "productoId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", proveedorId='" + proveedorId + '\'' +
                ", unidad='" + unidad + '\'' +
                '}';
    }
}
