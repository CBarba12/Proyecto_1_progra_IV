package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor", schema = "proyecto_1")
public class ProveedorEntity {

    @Id
    @Column(name = "id_proveedor")
    private String idProveedor;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
    @Basic
    @Column(name = "estado")
    private String estado;
    @Basic
    @Column(name = "admin")
    private Byte admin;
    @Basic
    @Column(name = "telefono")
    private Integer telefono;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "tipo_proveedor")
    private String tipoProveedor;
    @Basic
    @Column(name = "actividad_comercial")
    private String actividadComercial;

    public ProveedorEntity(String idProveedor, String nombre, String correoElectronico, String contrasena, String estado, Byte admin, Integer telefono, String direccion, String tipoProveedor, String actividadComercial) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.estado = estado;
        this.admin = admin;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoProveedor = tipoProveedor;
        this.actividadComercial = actividadComercial;
    }

    public ProveedorEntity() {
    }


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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Byte getAdmin() {
        return admin;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
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

    public String getActividadComercial() {
        return actividadComercial;
    }

    public void setActividadComercial(String actividadComercial) {
        this.actividadComercial = actividadComercial;
    }




}
