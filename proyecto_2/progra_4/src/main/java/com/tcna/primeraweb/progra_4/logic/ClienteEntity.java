package com.tcna.primeraweb.progra_4.logic;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class ClienteEntity {

    private String clienteId;

    private String nombre;

    private String direccion;

    private String tipoCliente;

    private String correoElectronico;

    private String proveedorId;

    public ClienteEntity() {
    }
    public ClienteEntity(String clienteId, String nombre, String direccion, String tipoCliente, String correoElectronico, String proveedorId) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
        this.correoElectronico = correoElectronico;
        this.proveedorId = proveedorId;
    }
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }
}
