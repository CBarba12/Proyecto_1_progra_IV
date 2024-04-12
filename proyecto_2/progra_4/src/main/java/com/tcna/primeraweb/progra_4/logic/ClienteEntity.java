package com.tcna.primeraweb.progra_4.logic;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente", schema = "proyecto_2")

@Data

public class ClienteEntity
{

    @Id
    String cliente_id ;
    String nombre ;
    String direccion ;
    String tipo_cliente;
    String correo_electronico ;
    String proveedor_id ;

    public ClienteEntity(String cliente_id, String nombre, String direccion, String tipo_cliente, String correo_electronico, String proveedor_id) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo_cliente = tipo_cliente;
        this.correo_electronico = correo_electronico;
        this.proveedor_id = proveedor_id;
    }

    public ClienteEntity() {
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(String proveedor_id) {
        this.proveedor_id = proveedor_id;
    }
}
