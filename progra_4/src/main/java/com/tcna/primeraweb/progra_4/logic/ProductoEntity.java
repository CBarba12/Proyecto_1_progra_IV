package com.tcna.primeraweb.progra_4.logic;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto", schema = "proyecto_1")

@Data

public class ProductoEntity {
    @Id
    int producto_id ;
    String nombre ;
    String descripcion;
    float precio ;
    String tipo_producto ;
    String proveedor_id ;

    public ProductoEntity() {
    }

    public ProductoEntity(int producto_id, String nombre, String descripcion, float precio, String tipo_producto, String proveedor_id) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo_producto = tipo_producto;
        this.proveedor_id = proveedor_id;
    }


    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(String proveedor_id) {
        this.proveedor_id = proveedor_id;
    }
}
