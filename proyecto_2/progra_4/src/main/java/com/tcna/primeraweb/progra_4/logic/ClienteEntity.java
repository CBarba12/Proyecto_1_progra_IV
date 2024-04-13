package com.tcna.primeraweb.progra_4.logic;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente", schema = "proyecto_1")
public class ClienteEntity {
    @Id
    @Column(name = "cliente_id")
    private String clienteId;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "tipo_cliente")
    private String tipoCliente;
    @Basic
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic
    @Column(name = "proveedor_id")
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

    public void setCliente_id(String clienteId) {
        if (this.tipoCliente.equals("fisico") && clienteId.length() != 9) {
            throw new IllegalArgumentException("El ID del cliente debe tener 9 dígitos para clientes físicos");
        } else if (this.tipoCliente.equals("juridico") && clienteId.length() != 10) {
            throw new IllegalArgumentException("El ID del cliente debe tener 10 dígitos para clientes jurídicos");
        }
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
