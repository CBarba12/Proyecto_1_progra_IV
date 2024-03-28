package com.tcna.primeraweb.project_progra.logic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProveedorEntity {

    @Id
    @Column(name = "ID_Proveedor")
    private String idProveedor;

    @Basic
    @Column(name = "Nombre")
    private String nombre;

    @Basic
    @Column(name = "CorreoElectronico")
    private String correoElectronico;

    @Basic
    @Column(name = "Contrasena")
    private String contrasena;
    @Basic

    private Byte activo;
    @Basic
    @Column(name = "Telefono")
    private Integer telefono;
    @Basic
    @Column(name = "Direccion")
    private String direccion;
    @Basic
    @Column(name = "TipoProveedor")
    private String tipoProveedor;
    @Basic
    @Column(name = "perfilHacienda")
    private String perfilHacienda;

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
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

        ProveedorEntity that = (ProveedorEntity) o;

        if (idProveedor != null ? !idProveedor.equals(that.idProveedor) : that.idProveedor != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (correoElectronico != null ? !correoElectronico.equals(that.correoElectronico) : that.correoElectronico != null)
            return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;
        if (activo != null ? !activo.equals(that.activo) : that.activo != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (tipoProveedor != null ? !tipoProveedor.equals(that.tipoProveedor) : that.tipoProveedor != null)
            return false;
        if (perfilHacienda != null ? !perfilHacienda.equals(that.perfilHacienda) : that.perfilHacienda != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProveedor != null ? idProveedor.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (correoElectronico != null ? correoElectronico.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (tipoProveedor != null ? tipoProveedor.hashCode() : 0);
        result = 31 * result + (perfilHacienda != null ? perfilHacienda.hashCode() : 0);
        return result;
    }
}
