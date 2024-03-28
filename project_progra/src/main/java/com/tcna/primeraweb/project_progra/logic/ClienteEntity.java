package com.tcna.primeraweb.project_progra.logic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente", schema = "proyecto_1")
@Data
@NoArgsConstructor
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
    @Column(name = "TipoCliente")
    private String tipoCliente;
    @Basic
    @Column(name = "perfilHacienda")
    private String perfilHacienda;

    public ClienteEntity(String clienteId, String nombre, String direccion, String tipoCliente, String perfilHacienda) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
        this.perfilHacienda = perfilHacienda;
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

    public String getPerfilHacienda() {
        return perfilHacienda;
    }

    public void setPerfilHacienda(String perfilHacienda) {
        this.perfilHacienda = perfilHacienda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (clienteId != null ? !clienteId.equals(that.clienteId) : that.clienteId != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (tipoCliente != null ? !tipoCliente.equals(that.tipoCliente) : that.tipoCliente != null) return false;
        if (perfilHacienda != null ? !perfilHacienda.equals(that.perfilHacienda) : that.perfilHacienda != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clienteId != null ? clienteId.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (tipoCliente != null ? tipoCliente.hashCode() : 0);
        result = 31 * result + (perfilHacienda != null ? perfilHacienda.hashCode() : 0);
        return result;
    }
}
